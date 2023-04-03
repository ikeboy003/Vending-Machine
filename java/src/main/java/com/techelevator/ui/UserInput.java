package com.techelevator.ui;

import com.techelevator.application.VendingMachine;

import com.techelevator.exceptions.NotSuchFoodException;
import com.techelevator.models.Customer;
import com.techelevator.models.Food;

import javax.swing.text.html.Option;
import java.math.BigDecimal;
import java.util.*;

/**
 * Responsibilities: This class should handle receiving ALL input from the User
 * 
 * Dependencies: None
 */
public class UserInput
{
    private static Scanner scanner = new Scanner(System.in);
    private List<Food> selectedList = new ArrayList<>();
    UserOutput output = new UserOutput();


    public String getHomeScreenOption()
    {
        System.out.println("What would you like to do?");
        System.out.println();

        System.out.println("D) Display Vending Machine Items");
        System.out.println("P) Purchase");
        System.out.println("E) Exit");

        System.out.println();
        System.out.print("Please select an option: ");

        String selectedOption = scanner.nextLine();
        String option = selectedOption.trim().toLowerCase();

        if (option.equals("d"))
        {
            return "display";
        }
        else if (option.equals("p"))
        {
            return "purchase";
        }
        else if (option.equals("e"))
        {
            return "exit";
        }
        else
        {
            return "";
        }

    }

    public void getPurchaseInputOption(VendingMachine machine,Customer customer){
        String [] prompts = {"(M) Feed Money",
                "(S) Select Item",
                "(F) Finish Transaction"};

        System.out.println("What would you like to do");
        for(String s: prompts){
            System.out.println(s);
        }
        System.out.println("Current Money Provided: "+ customer.getCurrentMoneyProvided());
        System.out.print("\nPlease select an option: ");

        String option = scanner.nextLine().toLowerCase();

        if (option.equals("m"))
        {
            userFeedMoney(machine,customer);
        }
        else if (option.equals("s"))
        {
            choiceSelectItem(machine,customer);
        }
        else if (option.equals("f"))
        {
            finishTransaction(machine,customer);
            System.out.println("Thank you! Please come again.");
        }
        else
        {
            userFeedMoney(machine,customer);
        }

    }


    public void userFeedMoney(VendingMachine machine,Customer customer) {
        boolean boolChoice = true;

        while (boolChoice) {
            System.out.print("Please enter bill ($1, $2, $5, $10, $20): ");
            BigDecimal amount = new BigDecimal(scanner.nextLine());

            //check if it is the correct denomiation
            customer.feedMoney(amount);
            System.out.println(customer.getCurrentMoneyProvided());
            System.out.println("Would you like to continue adding money Y/N");
            boolChoice = scanner.nextLine().equalsIgnoreCase("Y");
        }
        getPurchaseInputOption(machine,customer);
    }

    public void choiceSelectItem(VendingMachine vendingMachine,Customer customer){


        vendingMachine.displayItems();
        System.out.print("Please enter the item you want to select: ");
        boolean isDiscountSale;
        try {
            Food snack = vendingMachine.getFoodItem(scanner.nextLine());
            if(customer.getCurrentMoneyProvided().compareTo(snack.getPrice())>0){
                if(customer.getItemsPurchased()!=0 && customer.getItemsPurchased()%2==0){
                    isDiscountSale = true;
                    snack.applyDiscount();
                    customer.makePurchase(snack,isDiscountSale);
                    customer.incrementItemsPurchased();

                }else{
                    isDiscountSale = false;
                    customer.makePurchase(snack,isDiscountSale);
                    customer.incrementItemsPurchased();
                }

                vendingMachine.dcrementQuantity(snack);
             } else {
                System.out.println("You do not have enough money to make a selection");
                userFeedMoney(vendingMachine,customer);
            }
        }catch (NotSuchFoodException e){
            System.out.println(e.getMessage());
        }


    }
    public void finishTransaction(VendingMachine machine,Customer customer) {
        customer.makeChange();

    }
    public void inishTransaction(VendingMachine machine,Customer customer) {

        // Make Change
        BigDecimal totalPrice = new BigDecimal("0.00");
        for (int i = 0; i < selectedList.size(); i++) {
          if (i % 2 == 0) {
              totalPrice = totalPrice.add(selectedList.get(i).getPrice().subtract(new BigDecimal("1.00")));
          } else {
              totalPrice = totalPrice.add(selectedList.get(i).getPrice());
          }
        }
        if (totalPrice.compareTo(customer.getCurrentMoneyProvided()) <= 0) {
            System.out.println("Purchase was successful!");



            selectedList.clear();
        } else {
            System.out.println("Insufficient funds, please insert the correct amount.");
            System.out.println("Current funds: " + customer.getCurrentMoneyProvided() + " Money needed: " + totalPrice);

        }
    }




}
