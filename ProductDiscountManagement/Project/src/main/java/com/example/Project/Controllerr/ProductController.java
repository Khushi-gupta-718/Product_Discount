package com.example.Project.Controllerr;

import com.example.Project.model.Product;
import com.example.Project.Service.ProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping("/discount")
    public ResponseEntity<Product> applyDiscount(@Valid @RequestBody Product product) {
        return ResponseEntity.ok(productService.applyDiscount(product));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable String id) {
        return ResponseEntity.ok(productService.getProduct(id));
    }
}
