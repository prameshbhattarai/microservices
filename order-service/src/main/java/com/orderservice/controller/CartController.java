package com.orderservice.controller;

import com.orderservice.entity.Item;
import com.orderservice.service.CartService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cart")
@Log4j2
public class CartController {

    private final CartService cartService;

    @Autowired
    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping
    public ResponseEntity<List<Item>> getCart(@RequestHeader(value = "CartId") String cartId) {
        List<Item> cart = cartService.getCart(cartId);
        if (!cart.isEmpty()) {
            log.info("Found {} items in cart", cart.size());
            return new ResponseEntity<List<Item>>(cart, HttpStatus.OK);
        }
        log.error("Unable to find cart with id: {}", cartId);
        return new ResponseEntity<List<Item>>(HttpStatus.NOT_FOUND);
    }

    @PostMapping(params = {"productId, quantity"})
    public ResponseEntity<List<Item>> addItemToCart(@RequestParam("productId") Long productId,
                                                    @RequestParam("quantity") Integer quantity,
                                                    @RequestHeader(value = "CartId") String cartId) {
        List<Item> cart = cartService.getCart(cartId);
        if (cart != null) {
            if (cartService.checkIfItemIsExist(cartId, productId)) {
                log.info("Update cart: {} product: {} with quantity: {}", cartId, productId, quantity);
                cartService.changeItemQuantity(cartId, productId, quantity);
            } else {
                log.info("Add product: {} with quantity: {} in cart: {} ", productId, quantity, cartId);
                cartService.addItemToCart(cartId, productId, quantity);
            }
            cart = cartService.getCart(cartId);
            return new ResponseEntity<>(cart, HttpStatus.CREATED);
        }
        log.error("Unable to find cart with id: {}", cartId);
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping(params = "productId")
    public ResponseEntity<Void> removeItemFromCart(@RequestParam("productId") Long productId,
                                                   @RequestHeader(value = "CartId") String cartId) {
        List<Item> cart = cartService.getCart(cartId);
        if (cart != null) {
            cartService.deleteItemFromCart(cartId, productId);
            log.info("Remove product: {} from cart", productId);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        log.error("Unable to find cart with id: {}", cartId);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
