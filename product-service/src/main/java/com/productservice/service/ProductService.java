package com.productservice.service;

import com.productservice.entity.Product;

import java.util.List;

public interface ProductService {
    List<Product> getAllProduct();
    List<Product> getAllProductByCategory(String category);
    Product getProductById(Long id);
    List<Product> getAllProductByName(String productName);
    Product addProduct(Product product);
    void deleteProduct(Long id);
}
