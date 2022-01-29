package com.productservice.controller;

import com.productservice.entity.Product;
import com.productservice.service.ProductService;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> products = productService.getAllProduct();
        if (!products.isEmpty()) {
            return new ResponseEntity<List<Product>>(products, HttpStatus.OK);
        }
        return new ResponseEntity<List<Product>>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(params = "category")
    public  ResponseEntity<List<Product>> getAllProductsByCategory(@Param("category") String category) {
        List<Product> products = productService.getAllProductByCategory(category);
        if (!products.isEmpty()) {
            return new ResponseEntity<List<Product>>(products, HttpStatus.OK);
        }
        return new ResponseEntity<List<Product>>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/{id}")
    public  ResponseEntity<Product> getProductById(@PathVariable("id") Long id) {
        Product product = productService.getProductById(id);
        if (product != null) {
            return new ResponseEntity<>(product, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(params = "category")
    public  ResponseEntity<List<Product>> getAllProductsByProductName(@Param("name") String name) {
        List<Product> products = productService.getAllProductByName(name);
        if (!products.isEmpty()) {
            return new ResponseEntity<List<Product>>(products, HttpStatus.OK);
        }
        return new ResponseEntity<List<Product>>(HttpStatus.NOT_FOUND);
    }
}
