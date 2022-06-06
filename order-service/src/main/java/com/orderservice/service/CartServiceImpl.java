package com.orderservice.service;

import com.orderservice.entity.Item;
import com.orderservice.entity.Product;
import com.orderservice.feignClient.ProductClient;
import com.orderservice.redisRepository.CartRedisRepository;
import com.orderservice.utils.CartUtilities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartServiceImpl implements CartService {

    private final ProductClient productClient;
    private final CartRedisRepository cartRedisRepository;

    @Autowired
    public CartServiceImpl(CartRedisRepository cartRedisRepository, ProductClient productClient) {
        this.productClient = productClient;
        this.cartRedisRepository = cartRedisRepository;
    }

    @Override
    public void addItemToCart(String cartId, Long productId, Integer quantity) {
        Product product = productClient.getProductById(productId);
        Item item = new Item(quantity, product, CartUtilities.getSubTotalForItem(product, quantity));
        cartRedisRepository.addItemToCart(cartId, item);
    }

    @Override
    public List<Item> getCart(String cartId) {
        return cartRedisRepository.getCart(cartId, Item.class);
    }

    @Override
    public void changeItemQuantity(String cartId, Long productId, Integer quantity) {
        List<Item> cart = cartRedisRepository.getCart(cartId, Item.class);
        cart.stream().filter(item -> item.getProduct().getId().equals(productId))
                .forEach(item -> {
                    cartRedisRepository.deleteItemFromCart(cartId, item);
                    item.setQuantity(quantity);
                    item.setSubTotal(CartUtilities.getSubTotalForItem(item.getProduct(), quantity));
                    cartRedisRepository.addItemToCart(cartId, item);
                });
    }

    @Override
    public void deleteItemFromCart(String cartId, Long productId) {
        List<Item> cart = cartRedisRepository.getCart(cartId, Item.class);
        for (Item item : cart) {
            if ((item.getProduct().getId()).equals(productId)) {
                cartRedisRepository.deleteItemFromCart(cartId, item);
            }
        }
    }

    @Override
    public boolean checkIfItemIsExist(String cartId, Long productId) {
        List<Item> cart = cartRedisRepository.getCart(cartId, Item.class);
        for (Item item : cart) {
            if ((item.getProduct().getId()).equals(productId)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public List<Item> getAllItemsFromCart(String cartId) {
        return cartRedisRepository.getCart(cartId, Item.class);
    }

    @Override
    public void deleteCart(String cartId) {
        cartRedisRepository.deleteCart(cartId);
    }
}
