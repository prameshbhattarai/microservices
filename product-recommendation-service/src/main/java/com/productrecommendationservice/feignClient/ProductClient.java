package com.productrecommendationservice.feignClient;

import com.productrecommendationservice.entity.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

// dynamic feign client url detection
// it will get the client url from eureka server, 
// where we have registered our client with name ${product.service.name}
// and if it is not found in eureka server or eureka server is down
// then fall back to http://localhost:8810/
@FeignClient(value = "${product.service.name}", url = "http://localhost:8810/")
public interface ProductClient {

    @GetMapping(value = "/products/{id}")
    Product getProductById(@PathVariable(value = "id") Long productId);
}
