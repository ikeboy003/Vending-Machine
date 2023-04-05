package com.techelevator.models;

import java.math.BigDecimal;

public class Customer {
    private BigDecimal currentFunds;

    public Customer() {
        this.currentFunds = new BigDecimal("0.00");
    }

    public BigDecimal getCurrentFunds() {
        return this.currentFunds;
    }

    public void addFunds(BigDecimal newFunds) {
        currentFunds = currentFunds.add(newFunds);
    }

    public void purchaseItem(BigDecimal itemCost) {
        if (currentFunds.subtract(itemCost).compareTo(new BigDecimal("0.00")) >= 0) {
            currentFunds = currentFunds.subtract(itemCost);
        }
    }

}
