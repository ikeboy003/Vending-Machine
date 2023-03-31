package com.techelevator.models;

import java.math.BigDecimal;

public class Drink extends Food{

    public Drink(String itemLocation, String name, BigDecimal price) {
        super(itemLocation, name, price, "Drink");
    }

}
