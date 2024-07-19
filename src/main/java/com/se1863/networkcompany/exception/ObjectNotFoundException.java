package com.se1863.networkcompany.exception;

public class ObjectNotFoundException extends RuntimeException {
    public ObjectNotFoundException(String id) {
        super("Object with ID " +id+ " not found. Please check your input.");
    }
}
