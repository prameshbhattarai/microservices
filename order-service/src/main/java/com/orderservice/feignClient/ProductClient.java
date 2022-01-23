package com.orderservice.feignClient;

import com.orderservice.entity.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "product-catalog-service", url = "http://localhost:8810/")
public interface ProductClient {

    @GetMapping(value = "/products/{id}")
    Product getProductById(@PathVariable(value = "id") Long productId);
}
