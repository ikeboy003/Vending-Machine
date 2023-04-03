package com.techelevator.exceptions;

import java.util.NoSuchElementException;

public class NotSuchFoodException extends NoSuchElementException {

    public NotSuchFoodException(String message) {
        super(message);
    }
}
