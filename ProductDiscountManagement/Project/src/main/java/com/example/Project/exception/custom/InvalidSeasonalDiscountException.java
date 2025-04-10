package com.example.Project.exception.custom;

public class InvalidSeasonalDiscountException extends RuntimeException implements Runnable {

    public InvalidSeasonalDiscountException() {
        super("Seasonal discount is not valid at this time.");
    }

    @Override
    public void run() {
        throw new InvalidSeasonalDiscountException();
    }
}
