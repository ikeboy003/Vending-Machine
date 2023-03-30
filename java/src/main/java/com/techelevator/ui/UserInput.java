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
            //This is where
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
    public String getPurchaseInputOption(BigDecimal amountProvided){
        String [] prompts = {"(M) Feed Money",
                "(S) Select Item",
                "(F) Finish Transaction"};

        System.out.println("What would you like to do");
        for(String s: prompts){
            System.out.println(s);
        }
        System.out.println("Current Money Provided:"+ amountProvided);
        System.out.print("\nPlease select an option: ");

        String option = scanner.nextLine().toLowerCase();

        if (option.equals("m"))
        {
            return "feed money";
            //This is where
        }
        else if (option.equals("s"))
        {
            return "select item";
        }
        else if (option.equals("f"))
        {
            return "finish transaction";
        }
        else
        {
            return "";
        }

    }
    public String getPurchaseInputOption(){

        String [] prompts = {"(M) Feed Money",
                "(S) Select Item",
                "(F) Finish Transaction"};

        System.out.println("What would you like to do");
        for(String s: prompts){
            System.out.println(s);
        }
        System.out.println("Current Money Provided:");
        System.out.print("\nPlease select an option: ");

        String option = scanner.nextLine().toLowerCase();

        if (option.equals("m"))
        {
            return "feed money";
            //This is where
        }
        else if (option.equals("s"))
        {
            return "select item";
        }
        else if (option.equals("f"))
        {
            return "finish transaction";
        }
        else
        {
            return "";
        }

    }

    public static Scanner getScanner() {
        return scanner;
    }
}
