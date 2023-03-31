package com.techelevator.ui;

import com.techelevator.models.Food;
import java.util.List;



/**
 * Responsibilities: This class should handle formatting and displaying ALL
 * messages to the user
 * 
 * Dependencies: None
 */
public class UserOutput
{

    public void displayMessage(String message) {
        System.out.println(message);
    }


    public void displayHomeScreen() {
        System.out.println();
        System.out.println("***************************************************");
        System.out.println("                      Home");
        System.out.println("***************************************************");
        System.out.println();
    }

    public void displayFoodList(List<Food> foodList) {
        for (Food f : foodList) {
            String quantity = String.valueOf(f.getQuantity());
            if (f.getQuantity() ==0) {
                quantity = "NO LONGER AVAILABLE";
            }
            System.out.println(f.getItemLocation()
                    +", "+ f.getName()
                    +" $"+ f.getPrice()
                    + " Quantity: " + quantity);
        }
    }

    

}
