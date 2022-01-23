package com.orderservice.controller;

import com.orderservice.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/cart")
public class CartController {

    private final CartService cartService;

    @Autowired
    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping
    public ResponseEntity<List<Object>> getCart(@RequestHeader(value = "Cookie") String cartId) {
        List<Object> cart = cartService.getCart(cartId);
        if (!cart.isEmpty()) {
            return new ResponseEntity<List<Object>>(cart, HttpStatus.OK);
        }
        return new ResponseEntity<List<Object>>(HttpStatus.NOT_FOUND);
    }
}
