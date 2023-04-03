package com.techelevator.application;

import com.techelevator.models.Food;
import com.techelevator.models.FoodType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class VendingMachineTest {
    @Nested
    @DisplayName("Vending Machine Must")
    class VendingMust{
        VendingMachine machine ;
        Food testFood;
        @BeforeEach
        void setUp(){
            machine = new VendingMachine();
           testFood = new Food("A1","U-Chews",new BigDecimal("1.65"), FoodType.GUM) {
                @Override
                public void printMessage() {

                }
            };
        }

        @Test
        @DisplayName("Must Get Dipslay Location")
        void getFoodItem() {

            assertEquals(testFood.getItemLocation(),machine.getFoodItem("a1").getItemLocation());
        }

        @Test
        @DisplayName("Get Food Quantity")
        void getFoodQuantity() {
            assertEquals(testFood.getQuantity(),machine.getFoodItem("A1").getQuantity());

        }


        @Test
        @DisplayName("Must Decrement Quantity")
        void dcrementQuantity() {
            testFood.decrementQuantity();
            machine.getFoodItem("a1").decrementQuantity();
            assertEquals(testFood.getQuantity(),machine.getFoodItem("A1").getQuantity());
        }

    }

}