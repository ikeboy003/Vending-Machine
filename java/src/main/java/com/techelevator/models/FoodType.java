package com.techelevator.models;

public enum FoodType {
    CANDY,MUNCHY,DRINK,GUM;

    public static FoodType fromString(String str) {
        switch (str.trim().toUpperCase()) {
            case "CANDY":
                return CANDY;
            case "DRINK":
                return DRINK;
            case "GUM":
                return GUM;
            default:
                return MUNCHY;
        }
    }
}
