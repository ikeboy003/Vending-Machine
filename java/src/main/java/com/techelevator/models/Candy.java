package com.techelevator.models;

import java.math.BigDecimal;

public class Candy extends Food{

    public Candy(String itemLocation, String name, BigDecimal price) {
        super(itemLocation, name, price, "Candy");
    }

}
