package com.techelevator.models;

import java.math.BigDecimal;

public class Munchy extends Food {

    public Munchy(String itemLocation, String name, BigDecimal price) {
        super(itemLocation, name, price, FoodType.MUNCHY);
    }


    @Override
    public void printMessage() {
        System.out.println("Munchy, Munchy, so Good!");
    }
}
