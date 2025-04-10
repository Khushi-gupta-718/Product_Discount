package com.example.Project.Service;

import com.example.Project.exception.custom.*;
import com.example.Project.model.Product;
import com.example.Project.Repository.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    private static final Logger logger = LoggerFactory.getLogger(ProductService.class);

    @Autowired
    private ProductRepository productRepository;

    public Product applyDiscount(Product product) {
        logger.trace("Applying discount to product: {}", product);

        if (product.getQuantity() <= 0) {
            new OutOfStockException(product.getProductId()).run();
        }

        double discountAmount = 0;

        if ("flat".equalsIgnoreCase(product.getDiscountType())) {
            discountAmount = product.getDiscountValue();
        } else if ("percentage".equalsIgnoreCase(product.getDiscountType())) {
            discountAmount = product.getProductPrice() * product.getDiscountValue() / 100;
        } else {
            logger.error("Invalid discount type provided: {}", product.getDiscountType());
            new InvalidDiscountTypeException(product.getDiscountType()).run();
        }

        if (product.isSeasonalDiscountActive()) {
            discountAmount += product.getProductPrice() * 0.25;
            logger.trace("Seasonal discount applied");
        }

        if (discountAmount > product.getProductPrice()) {
            new DiscountExceedsPriceException().run();
        }

        double finalPrice = product.getProductPrice() - discountAmount;
        product.setFinalPrice(Math.max(0, finalPrice)); // Prevent negative price

        return productRepository.save(product);
    }

    public Product getProduct(String id) {
        logger.trace("Fetching product with id: {}", id);

        return productRepository.findById(id)
                .orElseThrow(() -> {
                    new ProductNotFoundException(Long.parseLong(id)).run();
                    return null; // Will never reach here
                });
    }
}
