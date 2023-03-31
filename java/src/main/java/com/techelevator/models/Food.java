package com.techelevator.models;

import java.math.BigDecimal;

public abstract class Food implements Sellable{
    private String itemLocation;
    private String name;
    private BigDecimal price;
    private FoodType foodType;
    private int quantity;

    public Food(String itemLocation, String name, BigDecimal price, FoodType foodType) {
        this.itemLocation = itemLocation;
        this.name = name;
        this.price = price;
        this.foodType = foodType;
        this.quantity = 6;
    }

    public String getItemLocation() {
        return itemLocation;
    }

    public String getName() {
        return name;
    }

    public FoodType getFoodType() {
        return foodType;
    }

    public int getQuantity() {
        return quantity;
    }

    public void decrementQuantity() {
        --quantity;
    }


    @Override
    public BigDecimal getPrice() {
        return price;
    }

}
