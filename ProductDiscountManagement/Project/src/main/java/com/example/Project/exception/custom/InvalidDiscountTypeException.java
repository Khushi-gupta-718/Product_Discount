package com.example.Project.exception.custom;

public class InvalidDiscountTypeException extends RuntimeException implements Runnable {
    private final String type;

    public InvalidDiscountTypeException(String type) {
        super("Invalid discount type: " + type);
        this.type = type;
    }

    @Override
    public void run() {
        throw new InvalidDiscountTypeException(type);
    }

}
