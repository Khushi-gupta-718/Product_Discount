package com.example.Project.exception;

import com.example.Project.exception.custom.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    // 🟢 Specific Custom Exceptions Handling

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<String> handleProductNotFound(ProductNotFoundException ex) {
        logger.error("Product not found: ", ex);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(DiscountExceedsPriceException.class)
    public ResponseEntity<String> handleDiscountExceeds(DiscountExceedsPriceException ex) {
        logger.error("Discount too high: ", ex);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    @ExceptionHandler(InvalidDiscountTypeException.class)
    public ResponseEntity<String> handleInvalidDiscountType(InvalidDiscountTypeException ex) {
        logger.error("Invalid discount type: ", ex);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    @ExceptionHandler(OutOfStockException.class)
    public ResponseEntity<String> handleOutOfStock(OutOfStockException ex) {
        logger.error("Out of stock: ", ex);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    @ExceptionHandler(InvalidSeasonalDiscountException.class)
    public ResponseEntity<String> handleInvalidSeason(InvalidSeasonalDiscountException ex) {
        logger.error("Invalid seasonal discount: ", ex);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    // 🔁 Fallback for any RuntimeException not caught above
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> handleRuntimeException(RuntimeException ex) {
        logger.error("Runtime exception occurred: ", ex);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body("Runtime error: " + ex.getMessage());
    }

    // 🔁 Catch-all for all other Exceptions
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleOtherExceptions(Exception ex) {
        if (ex instanceof RuntimeException) {
            // Avoid double-handling
            return null;
        }
        logger.error("Other exception occurred: ", ex);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Internal server error: " + ex.getMessage());
    }
}
