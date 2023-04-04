package com.techelevator.application;

import com.techelevator.audit.AuditLogger;
import com.techelevator.models.*;
import com.techelevator.ui.UserInput;
import com.techelevator.ui.UserOutput;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;

public class VendingMachine {
    private UserOutput userOutput;
    private UserInput userInput;
    private List<Food> listOfFood = new ArrayList<>();
    private Customer customer = new Customer();
    private int itemsBought = 1;
    private AuditLogger logger;


    public VendingMachine(){
        this.userInput = new UserInput();
        this.userOutput = new UserOutput();
        this.logger = new AuditLogger("Audit.txt");
    }

    public void run()
    {
        readInFromFile();


        while(true)
        {
            userOutput.displayHomeScreen();
            String choice = userInput.getHomeScreenOption();

            if(choice.equals("display"))
            {
                userOutput.displayFoodList(listOfFood);
            }
            else if(choice.equals("purchase"))
            {
                while (true) {
                    String purchaseChoice = userInput.getPurchaseMenuOption(customer.getCurrentFunds());

                    if (purchaseChoice.equalsIgnoreCase("money"))
                    {
                        customer.addFunds(userInput.inputMoney(customer.getCurrentFunds()));
                    }
                    else if (purchaseChoice.equalsIgnoreCase("select"))
                    {

                        userOutput.displayFoodList(listOfFood);

                        dispenseItem(userInput.selectFoodItem());
                    }
                    else if (purchaseChoice.equalsIgnoreCase("finish"))
                    {
                        finishShopping();
                        break;
                    }
                    else
                    {
                        userOutput.displayMessage("Selection not found, please try again.");
                    }

                }

            }
            else if(choice.equals("exit"))
            {
                userOutput.displayMessage("Thank you for your patronage, please come again!");
                //closes the program either way, added sys.exit for clarity
                System.exit(0);
                break;
            }
        }
    }

    private void dispenseItem(String selectedFoodItem) {

        BigDecimal currentFunds = customer.getCurrentFunds();
        List<Food> foodToPurchase = new ArrayList<>();

        for (Food f : listOfFood) {
            if (selectedFoodItem.equals(f.getItemLocation().toLowerCase())) {
                foodToPurchase.add(f);
            }
        }

        Food food = foodToPurchase.get(0);
        //I know there's a better way to get the food object, but unless it's iterating through the types of food
        //and creating it's own object here, i'm doing it this way
        BigDecimal cost = foodToPurchase.get(0).getPrice();

        BigDecimal prevPurchaseFunds = customer.getCurrentFunds();

        if (currentFunds.compareTo(cost) < 0) {
            userOutput.displayMessage("Please add more funds to purchase your selection.");
        } else if (foodToPurchase.get(0).getQuantity() == 0) {
            userOutput.displayMessage("NO LONGER AVAILABLE");
        } else {
            if (itemsBought % 2 == 0) {
                cost = cost.subtract(new BigDecimal("1.00"));
            }
            customer.purchaseItem(cost);
            foodToPurchase.get(0).purchaseFood();
            itemsBought++;
            logger.write(food.getName() + " " + food.getItemLocation() + " $" + prevPurchaseFunds + " $" + customer.getCurrentFunds());
            userOutput.displayMessage("Thank you for your purchase of " + foodToPurchase.get(0).getName()
                    + " for $" + cost + ". You have $" + customer.getCurrentFunds() + " remaining."
            );
            switch (foodToPurchase.get(0).getType()) {
                case "Munchy":
                    userOutput.displayMessage("Munchy, Munchy, so Good!");
                    break;
                case "Candy":
                    userOutput.displayMessage("Sugar, Sugar, so Sweet!");
                    break;
                case "Gum":
                    userOutput.displayMessage("Chewy, Chewy, Lots O Bubbles!");
                    break;
                case "Drink":
                    userOutput.displayMessage("Drinky, Drinky, Slurp Slurp!");
                    break;
            }
        }
    }


    private void finishShopping() {
        BigDecimal change = customer.getCurrentFunds();
        Map<String, Integer> coinMap = new HashMap<>();
        Map<String, BigDecimal> coinValueMap = new HashMap<>();

        String dollarBills = String.valueOf(change.intValue());
        change = change.subtract(new BigDecimal(dollarBills));

        String quarters = "quarters";
        String dimes = "dimes";
        String nickles = "nickles";
        String pennies = "pennies";

        coinValueMap.put(quarters,  new BigDecimal("0.25"));
        coinValueMap.put(dimes, new BigDecimal("0.10"));
        coinValueMap.put(nickles, new BigDecimal("0.05"));
        coinValueMap.put(pennies, new BigDecimal("0.01"));

        coinMap.putAll(getChange(coinValueMap, change));

        BigDecimal totalChange = new BigDecimal(dollarBills).add(change);
        logger.write("CHANGE GIVEN: $" + totalChange + " $0.00");

        userOutput.displayMessage("Your change is $" + totalChange + " as "
                + dollarBills +" dollar bills, "
                + coinMap.get(quarters) +" quarters, "
                + coinMap.get(dimes) + " dimes, "
                + coinMap.get(nickles) + " nickles, and "
                + coinMap.get(pennies) + " pennies."
        );
    }

    private Map<String, Integer> getChange(Map<String, BigDecimal> coinValueMap, BigDecimal change) {
        Map<String, Integer> changeMap = new HashMap<>();
        BigDecimal zero = new BigDecimal("0.00");

        for (Map.Entry<String, BigDecimal> coin : coinValueMap.entrySet()) {
            changeMap.put(coin.getKey(), 0);

            while (change.compareTo(zero) > 0) {

                if (change.subtract(coin.getValue()).compareTo(zero) >= 0) {

                    change = change.subtract(coin.getValue());
                    changeMap.put(coin.getKey(), changeMap.get(coin.getKey())+1);

                } else {
                    break;
                }
            }
        }
        return changeMap;
    }

    //load values from file into a List<Food>
    private void readInFromFile() {
        File vendingFile = new File("catering.csv");

        logger.write("\nLOADED STOCK");

        try (Scanner fileScanner = new Scanner(vendingFile)) {
            while (fileScanner.hasNextLine()) {

                String line = fileScanner.nextLine();
                String[] foodProperties = line.split(",");

                String location = foodProperties[0];
                String name = foodProperties[1];
                BigDecimal price = new BigDecimal(foodProperties[2]);

                switch (foodProperties[3].toUpperCase()) {
                    case "CANDY":
                        listOfFood.add(new Candy(location, name, price));
                        break;
                    case "GUM":
                        listOfFood.add(new Gum(location, name, price));
                        break;
                    case "DRINK":
                        listOfFood.add(new Drink(location, name, price));
                        break;
                    default:
                        listOfFood.add(new Munchy(location, name, price));
                        break;
                }

            }

//            listFood.forEach(food -> System.out.println(food.getName()));

        } catch (FileNotFoundException e) {
            System.out.println("File not found, ending program");
            System.exit(0);
        }

    }
}