package com.techelevator.ui;

import com.techelevator.application.VendingMachine;
import com.techelevator.models.Customer;
import com.techelevator.models.Food;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Responsibilities: This class should handle receiving ALL input from the User
 * 
 * Dependencies: None
 */
public class UserInput
{
    private static Scanner scanner = new Scanner(System.in);
    private Customer customer = new Customer(new BigDecimal("0.00"));
    private List<Food> selectedList = new ArrayList<>();

    public  String getHomeScreenOption()
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
    public void getPurchaseInputOption(BigDecimal amountProvided){
        String [] prompts = {"(M) Feed Money",
                "(S) Select Item",
                "(F) Finish Transaction"};

        System.out.println("What would you like to do");
        for(String s: prompts){
            System.out.println(s);
        }
        System.out.println("Current Money Provided: "+ amountProvided);
        System.out.print("\nPlease select an option: ");

        String option = scanner.nextLine().toLowerCase();

        if (option.equals("m"))
        {
            userFeedMoney();
        }
        else if (option.equals("s"))
        {
            choiceSelectItem();
        }
        else if (option.equals("f"))
        {
            finishTransaction();
            System.out.println("Thank you! Please come again.");
        }
        else
        {
            userFeedMoney();
        }

    }
//    public String getPurchaseInputOption(){
//
//        String [] prompts = {"(M) Feed Money",
//                "(S) Select Item",
//                "(F) Finish Transaction"};
//
//        System.out.println("What would you like to do");
//        for(String s: prompts){
//            System.out.println(s);
//        }
//        System.out.println("Current Money Provided:");
//        System.out.print("\nPlease select an option: ");
//
//        String option = scanner.nextLine().toLowerCase();
//
//        if (option.equals("m"))
//        {
//            return "feed money";
//        }
//        else if (option.equals("s"))
//        {
//            return "select item";
//        }
//        else if (option.equals("f"))
//        {
//            return "finish transaction";
//        }
//        else
//        {
//            return "";
//        }
//
//    }

    public void userFeedMoney() {
        boolean boolChoice = true;


        while (boolChoice) {
            System.out.println("How Much money would you like to put in");
            BigDecimal amount = new BigDecimal(scanner.nextLine());
            customer.feedMoney(amount);
            System.out.println(customer.getCurrentMoneyProvided());
            System.out.println("Would you like to continue adding money Y/N");
            boolChoice = scanner.nextLine().equalsIgnoreCase("Y");
        }
        getPurchaseInputOption(customer.getCurrentMoneyProvided());
    }

    public void choiceSelectItem(){
        VendingMachine vendingMachine = new VendingMachine();
        vendingMachine.printList();
        System.out.println("Please Enter the item you want to select");

        while(true){
            try{
                Food item =  vendingMachine.getFoodItem(scanner.nextLine());
                selectedList.add(item);
                System.out.println(item.getName());
                break;
            }catch (NoSuchElementException e){
                System.out.println("Please enter a Correct Location");
            }
        }
    }

    public void finishTransaction() {
        VendingMachine vendingMachine = new VendingMachine();
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
            List<Food> foodList = vendingMachine.getListFood();
            for (Food f : selectedList) {
                if (foodList.contains(f)) {
                    foodList.get(foodList.indexOf(f)).decrementQuantity();
                }
            }
            customer.makePurchase(totalPrice);
        } else {
            System.out.println("Insufficient funds, please insert the correct amount.");
            System.out.println("Current funds: " + customer.getCurrentMoneyProvided() + " Money needed: " + totalPrice);

        }
    }

    public Customer getCustomer() {
        return this.customer;
    }


}
