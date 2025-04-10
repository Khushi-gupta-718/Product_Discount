package com.example.Project.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    @Id
    private String productId;
    private double productPrice;
    private int quantity;
    private String discountType;
    private double discountValue;
    private boolean seasonalDiscountActive;
    private double finalPrice;
	
}
