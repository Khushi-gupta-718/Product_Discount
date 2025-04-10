package com.example.Project.exception.custom;

public class DiscountExceedsPriceException extends RuntimeException implements Runnable 
{

    public DiscountExceedsPriceException() {
        super("Total discount cannot exceed product price.");
    }

    @Override
    public void run() {
        throw new DiscountExceedsPriceException();
    }
}