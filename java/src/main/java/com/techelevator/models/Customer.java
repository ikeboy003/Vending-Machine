package com.techelevator.models;

import java.math.BigDecimal;

public class Customer {
    private BigDecimal currentMoneyProvided;

    public Customer(BigDecimal currentMoneyProvided) {
        this.currentMoneyProvided = currentMoneyProvided;
    }

    public BigDecimal getCurrentMoneyProvided() {
        return currentMoneyProvided;
    }
    public void feedMoney(BigDecimal amountFed){
        currentMoneyProvided = currentMoneyProvided.add(amountFed);

    }
}
