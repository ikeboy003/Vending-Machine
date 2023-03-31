package com.techelevator.models;

import java.math.BigDecimal;

public class Customer {
    private BigDecimal currentFunds;

    public Customer() {
        this.currentFunds = new BigDecimal("0.0");
    }

    public BigDecimal getCurrentFunds() {
        return this.currentFunds;
    }

    public void addFunds(BigDecimal newFunds) {
        currentFunds = currentFunds.add(newFunds);
    }

    public void purchaseItem(BigDecimal itemCost) {
        currentFunds = currentFunds.subtract(itemCost);
    }

}
