package com.example.Project.exception.custom;

public class ProductNotFoundException  extends RuntimeException implements Runnable 
{
	    private final Long productId;

	    public ProductNotFoundException(Long productId) {
	        super("Product with ID " + productId + " not found.");
	        this.productId = productId;
	    }

	    @Override
	    public void run() {
	        throw new ProductNotFoundException(productId);
	    }
	}
