package com.techelevator.application;

import com.techelevator.models.*;
import com.techelevator.ui.UserInput;
import com.techelevator.ui.UserOutput;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class VendingMachine {
    private UserOutput userOutput;
    private UserInput userInput;
    private List<Food> listFood = new ArrayList<>();
    private Customer customer = new Customer(new BigDecimal("0.0"));

    public VendingMachine(){
        this.userInput = new UserInput();
        this.userOutput = new UserOutput();
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
              printList();

            }
            else if(choice.equals("purchase"))
            {;
                choice = userInput.getPurchaseInputOption(customer.getCurrentMoneyProvided());

                if(choice.equals("feed money")){
                    boolean boolChoice = true;


                    while(boolChoice){
                        System.out.println("How Much money would you like to put in");
                        BigDecimal amount = new BigDecimal(UserInput.getScanner().nextLine());
                        customer.feedMoney(amount);
                        System.out.println(customer.getCurrentMoneyProvided());
                        System.out.println("Would you like to continue adding money Y/N");
                        boolChoice = UserInput.getScanner().nextLine().equalsIgnoreCase("Y");
                    }


                }else if(choice.equals("select item")){
                    choiceSelectItem();
                }else if(choice.equals("finish transaction")){
                    System.out.println("finish transaction");
                }

                // make a purchase
            }
            else if(choice.equals("exit"))
            {
                // good bye
                break;
            }
        }
    }

    private void readInFromFile() {
        File vendingFile = new File("catering.csv");

        //logger later

        try (Scanner fileScanner = new Scanner(vendingFile)) {
            while (fileScanner.hasNextLine()) {

                String line = fileScanner.nextLine();
                String[] foodProperties = line.split(",");

                String location = foodProperties[0];
                String name = foodProperties[1];
                BigDecimal price = new BigDecimal(foodProperties[2]);

                switch (foodProperties[3].toUpperCase()) {
                    case "CANDY":
                        listFood.add(new Candy(location, name, price));
                        break;
                    case "GUM":
                        listFood.add(new Gum(location, name, price));
                        break;
                    case "DRINK":
                        listFood.add(new Drink(location, name, price));
                        break;
                    default:
                        listFood.add(new Munchy(location, name, price));
                        break;
                }

            }

//            listFood.forEach(food -> System.out.println(food.getName()));

        } catch (FileNotFoundException e) {
            System.out.println("File not found, ending program");
            System.exit(0);
        }

    }

    public Food getFoodItem(String location){
        return listFood.stream()
                .filter(foodItem -> foodItem.getItemLocation().toLowerCase().equals(location.toLowerCase()))
                .findFirst().orElseThrow(NoSuchElementException::new);
    }

    public void printList(){
        listFood.forEach(food -> {
            System.out.printf("%s %s $%s Quantity: %s \n", food.getItemLocation(), food.getName(), food.getPrice(), food.getQuantity() == 0 ? "NO LONGER AVAILABLE" : food.getQuantity());
        });
    }
    public void choiceSelectItem(){
        printList();
        System.out.println("Please Enter the item you want to select");

        while(true){
            try{
                Food item =  getFoodItem(UserInput.getScanner().nextLine());
                System.out.println(item.getName());
                break;
            }catch (NoSuchElementException e){
                System.out.println("Please enter a Correct Location");
            }
        }
    }

}
