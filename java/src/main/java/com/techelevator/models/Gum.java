package com.techelevator.models;

import java.math.BigDecimal;

public class Gum extends Food{

    public Gum(String itemLocation, String name, BigDecimal price) {
        super(itemLocation, name, price, FoodType.GUM);
    }

    @Override
    public void printMessage() {
        System.out.println("Chewy, Chewy, Lots O Bubbles!");
    }
}
