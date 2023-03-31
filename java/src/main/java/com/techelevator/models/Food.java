package com.techelevator.models;

import java.math.BigDecimal;

public abstract class Food {
    private String itemLocation;
    private String name;
    private BigDecimal price;
    private String type;
    private int quantity;

    public Food(String itemLocation, String name, BigDecimal price, String type) {
        this.itemLocation = itemLocation;
        this.name = name;
        this.price = price;
        this.type = type;
        this.quantity = 6;
    }

    public String getItemLocation() {
        return itemLocation;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void purchaseFood() {
        if (this.quantity > 0) {
            this.quantity--;
        }
    }

    public String getType() {
        return this.type;
    }

    public int getQuantity() {
        return quantity;
    }
}
