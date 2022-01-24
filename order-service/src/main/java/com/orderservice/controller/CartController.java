package com.orderservice.controller;

import com.orderservice.entity.Item;
import com.orderservice.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<List<Item>> getCart(@RequestHeader(value = "Cookie") String cartId) {
        List<Item> cart = cartService.getCart(cartId);
        if (!cart.isEmpty()) {
            return new ResponseEntity<List<Item>>(cart, HttpStatus.OK);
        }
        return new ResponseEntity<List<Item>>(HttpStatus.NOT_FOUND);
    }

    @PostMapping(params = {"productId, quantity"})
    public ResponseEntity<List<Item>> addItemToCart(@RequestParam("productId") Long productId,
                                                    @RequestParam("quantity") Integer quantity,
                                                    @RequestHeader(value = "Cookie") String cartId) {
        List<Item> cart = cartService.getCart(cartId);
        if (cart != null) {
            if (cartService.checkIfItemIsExist(cartId, productId)) {
                cartService.changeItemQuantity(cartId, productId, quantity);
            } else {
                cartService.addItemToCart(cartId, productId, quantity);
            }
            cart = cartService.getCart(cartId);
            return new ResponseEntity<>(cart, HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping(params = "productId")
    public ResponseEntity<Void> removeItemFromCart(@RequestParam("productId") Long productId,
                                                   @RequestHeader(value = "Cookie") String cartId) {
        List<Item> cart = cartService.getCart(cartId);
        if (cart != null) {
            cartService.deleteItemFromCart(cartId, productId);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
