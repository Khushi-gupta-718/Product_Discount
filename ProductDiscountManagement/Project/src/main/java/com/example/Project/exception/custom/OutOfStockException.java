package com.example.Project.exception.custom;

public class OutOfStockException extends RuntimeException implements Runnable {

    private final String productId;

    public OutOfStockException(String productId) {
        super("Product with ID " + productId + " is out of stock.");
        this.productId = productId;
    }

    @Override
    public void run() {
        throw this;
    }
}
