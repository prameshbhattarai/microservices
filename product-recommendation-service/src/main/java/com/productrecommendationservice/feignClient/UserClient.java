package com.productrecommendationservice.feignClient;

import com.productrecommendationservice.entity.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "user-service", url = "http://localhost:8810/")
public interface UserClient {

    @GetMapping(value = "/users/{id}")
    User getUserById(@PathVariable("id") Long id);
}
