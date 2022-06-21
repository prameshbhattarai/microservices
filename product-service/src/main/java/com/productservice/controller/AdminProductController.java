package com.productservice.controller;

import com.productservice.entity.Product;
import com.productservice.service.ProductService;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/admin/products")
@Log4j2
public class AdminProductController {

    private final ProductService productService;

    public AdminProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public ResponseEntity<Product> addProducts(@RequestBody Product product) {
        if (product != null) {
            try {
                Product createdProduct = productService.addProduct(product);
                log.info("Successfully saved product");
                return new ResponseEntity<Product>(createdProduct, HttpStatus.OK);
            } catch (Exception e) {
                log.error("Error while saving product with name: {}", product.getProductName(), e);
                e.printStackTrace();
                return new ResponseEntity<Product>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        log.error("Requesting product should not be null");
        return new ResponseEntity<Product>(HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable("id") Long id) {
        Product product = productService.getProductById(id);
        if (product != null) {
            try {
                productService.deleteProduct(id);
                log.info("Successfully deleted product with name: {} id: {}", product.getProductName(), id);
                return new ResponseEntity<>(HttpStatus.OK);
            } catch (Exception e) {
                e.printStackTrace();
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        log.error("Unable to get product by id: {}", id);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
