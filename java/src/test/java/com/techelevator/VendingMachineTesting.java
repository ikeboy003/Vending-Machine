package com.techelevator;

import com.techelevator.models.Customer;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;

public class VendingMachineTesting {

    @Test
    public void customer_generates_correctly() {
        Customer customer = new Customer();
        Assert.assertEquals((new BigDecimal("0.00")), customer.getCurrentFunds());
    }

    @Test
    public void customer_add_funds_adds_funds() {
        Customer customer = new Customer();

        customer.addFunds(new BigDecimal("10.00"));

        Assert.assertEquals((new BigDecimal("10.00")), customer.getCurrentFunds());
    }

    @Test
    public void customer_remove_funds_decreases_funds() {
        Customer customer = new Customer();
        customer.addFunds(new BigDecimal("10.00"));
        customer.purchaseItem(new BigDecimal("7.00"));
        Assert.assertEquals(new BigDecimal("3.00"), customer.getCurrentFunds());

        customer.purchaseItem(new BigDecimal("4.00"));
        //will not allow purchase, keeping previous funds
        Assert.assertEquals(new BigDecimal("3.00"), customer.getCurrentFunds());
    }


}
