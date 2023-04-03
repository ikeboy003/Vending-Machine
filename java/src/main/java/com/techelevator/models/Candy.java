package com.techelevator.models;

import java.math.BigDecimal;

public class Candy extends Food{

    public Candy(String itemLocation, String name, BigDecimal price) {
        super(itemLocation, name, price, FoodType.CANDY);
    }

    @Override
    public void printMessage() {
        System.out.println("Sugar, Sugar, so Sweet!");
    }
}
