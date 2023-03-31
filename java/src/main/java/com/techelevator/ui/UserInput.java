package com.techelevator.ui;


import java.math.BigDecimal;
import java.util.Scanner;

/**
 * Responsibilities: This class should handle receiving ALL input from the User
 * 
 * Dependencies: None
 */
public class UserInput
{
    private static Scanner scanner = new Scanner(System.in);

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

    public  String getPurchaseMenuOption(BigDecimal currentFunds)
    {
        System.out.println("\nWhat would you like to do?");
        System.out.println();

        System.out.println("M) Feed Money");
        System.out.println("S) Select Item");
        System.out.println("F) Finish Transaction");

        System.out.println();
        System.out.println("Current Money Provided: $" + currentFunds);
        System.out.println();
        System.out.print("Please select an option: ");

        String selectedOption = scanner.nextLine();
        String option = selectedOption.trim().toLowerCase();

        if (option.equals("m"))
        {
            return "money";
        }
        else if (option.equals("s"))
        {
            return "select";
        }
        else if (option.equals("f"))
        {
            return "finish";
        }
        else
        {
            return "";
        }

    }


    public BigDecimal inputMoney() {
        BigDecimal inputMoneyTotal = new BigDecimal("0.00");
        System.out.println("Please input valid bills into the machine, one at a time, " +
                "or type \"finished\" if you are finished");
        System.out.println("Valid bills are $1s, $5s, $10s, or $20s.");

        //simplifing code, used repeatedly
        while (true) {
            String nextLine = scanner.nextLine();
            //user exit
            if (nextLine.equalsIgnoreCase("finished")) {
                break;
            } else if (!nextLine.equals("1") && !nextLine.equals("5") && !nextLine.equals("10") && !nextLine.equals("20")) {
                System.out.println("Please insert a valid bill");
            } else {
                inputMoneyTotal = inputMoneyTotal.add(new BigDecimal(nextLine));
                System.out.println("Your added current funds are: $" + inputMoneyTotal);
            }
            //check if bill is valid, if not tell user, if so, add to total.


        }
        //exit
        return inputMoneyTotal;
    }


    public String selectFoodItem() {
        System.out.println("Type \"finished\" to finish browsing.");
        System.out.print("Please input the location of the item you wish to purchase: ");
        return scanner.nextLine();
    }


}
