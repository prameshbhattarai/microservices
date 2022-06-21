package com.orderservice.feignClient;


import com.orderservice.entity.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

// dynamic feign client url detection
// it will get the client url from eureka server, 
// where we have registered our client with name ${user.service.name}
// and if it is not found in eureka server or eureka server is down
// then fall back to http://localhost:8810/
@FeignClient(value = "${user.service.name}", url = "http://localhost:8810/")
public interface UserClient {

    @GetMapping("/user/{id}")
    User getUserById(@PathVariable("id") Long id);
}
