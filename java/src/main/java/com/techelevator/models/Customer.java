package com.techelevator.models;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Customer {
    private BigDecimal currentMoneyProvided;
    private  int itemsPurchased=0;

    public Customer(BigDecimal currentMoneyProvided) {
        this.currentMoneyProvided = currentMoneyProvided;
    }

    public BigDecimal getCurrentMoneyProvided() {
        return currentMoneyProvided;
    }

    public void feedMoney(BigDecimal amountFed){
        BigDecimal preMoney = currentMoneyProvided;
        currentMoneyProvided = currentMoneyProvided.add(amountFed);
        String line = String.format("MONEY FED: $%4s $%5s",preMoney,getCurrentMoneyProvided());
        auditSnapshot(line);
    }

    public void makePurchase(Food food, boolean isDiscountSale) {
        food.printMessage();
        BigDecimal preMoney = currentMoneyProvided;
        currentMoneyProvided = currentMoneyProvided.subtract(food.getPrice());
        String line = String.format("%-10s %-3s $%-4s $%-4s",food.getName(),food.getItemLocation(),preMoney,getCurrentMoneyProvided());
        auditSnapshot(line);

    }

    public void incrementItemsPurchased(){
        itemsPurchased++;
    }
    public int getItemsPurchased(){
        return this.itemsPurchased;
    }
    public void makeChange(){
         BigDecimal NICKEL = new BigDecimal("0.05");
         BigDecimal DIME = new BigDecimal("0.10");
         BigDecimal QUARTER = new BigDecimal("0.25");
         BigDecimal DOLLAR = new BigDecimal("1.00");

        BigDecimal remainingChange = getCurrentMoneyProvided();
        int numDollars = remainingChange.divideToIntegralValue(DOLLAR).intValueExact();
        remainingChange = remainingChange.remainder(DOLLAR);

        int numQuarters = remainingChange.divideToIntegralValue(QUARTER).intValueExact();
        remainingChange = remainingChange.remainder(QUARTER);

        int numDimes = remainingChange.divideToIntegralValue(DIME).intValueExact();
        remainingChange = remainingChange.remainder(DIME);

        int numNickels = remainingChange.divideToIntegralValue(NICKEL).intValueExact();
        remainingChange = remainingChange.remainder(NICKEL);
        this.currentMoneyProvided =remainingChange;

        System.out.printf("Returning %d dollars %d quarters %d dimes %d nickels\n",numDollars,numQuarters,numDimes,numNickels);

    }

    private void auditSnapshot(String line){
        line = String.format("%s %s",getDateTime(),line);
        try(BufferedWriter bw = new BufferedWriter(new FileWriter("audit.txt",true))){
            bw.write(line);
            bw.newLine();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
    private String getDateTime(){
        LocalDateTime dateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy hh:mm:ss a ");
        return dateTime.format(formatter);
    }



}
