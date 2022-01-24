package com.orderservice.repository;

import java.util.List;

public interface CartRedisRepository {

    void addItemToCart(String key, Object item);
    <T> List<T> getCart(String key, Class<T> type);
    void deleteItemFromCart(String key, Object item);
    void deleteCart(String key);

}
