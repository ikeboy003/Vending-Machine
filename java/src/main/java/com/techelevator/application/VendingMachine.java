package com.techelevator.application;

import com.techelevator.exceptions.NotSuchFoodException;
import com.techelevator.models.*;
import com.techelevator.ui.UserInput;
import com.techelevator.ui.UserOutput;

import javax.swing.text.html.Option;
import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.*;

public class VendingMachine {
    private UserOutput userOutput;
    private UserInput userInput;
    private List<Food> listFood = new ArrayList<>();
    private Customer customer;

    public VendingMachine(){
        readInFromFile();
        this.userInput = new UserInput();
        this.userOutput = new UserOutput();
        customer = new Customer(new BigDecimal("0.0"));
    }

    public void run()
    {

        while(true)
        {
            userOutput.displayHomeScreen();
            String choice = userInput.getHomeScreenOption();

            if(choice.equals("display"))
            {
              displayItems();

            }
            else if(choice.equals("purchase"))
            {
                userInput.getPurchaseInputOption(this,customer);

            }
            else if(choice.equals("exit"))
            {

                break;
            }
        }
    }

    private void readInFromFile() {

        //logger later


        try (Scanner fileScanner = new Scanner(new File("catering.csv"))) {
            while (fileScanner.hasNextLine()) {

                String fileLine = fileScanner.nextLine();
                String[] foodProperties = fileLine.split(",");

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
                .filter(foodItem -> foodItem.getItemLocation().toLowerCase().equals(location.toLowerCase())&&foodItem.getQuantity()>0)
                .findFirst().orElseThrow(() -> new NotSuchFoodException("Please Enter a currect location"));
    }

    public void dcrementQuantity(Food food){

        listFood.stream().filter(streamFood -> streamFood.equals(food)).findAny().get().decrementQuantity();

    }

    public void displayItems(){
        getListFood().forEach(food -> {
            System.out.printf("%s %s $%s Quantity: %s \n", food.getItemLocation(), food.getName(), food.getPrice(), food.getQuantity() == 0 ? "NO LONGER AVAILABLE" : food.getQuantity());
        });
    }

    public List<Food> getListFood() {
        return this.listFood;
    }

    public int getFoodQuantity(Food f) {

        return getFoodItem(f.getItemLocation()).getQuantity();


    }

}
