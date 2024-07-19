package com.se1863.networkcompany.exception;

public class IllegalPriceValueException extends RuntimeException {
    public IllegalPriceValueException(String id) {
        super("Price of option " +id+ " must be a POSITIVE number.");
    }
}
