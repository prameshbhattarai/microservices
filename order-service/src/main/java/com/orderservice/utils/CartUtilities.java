package com.orderservice.utils;

import com.orderservice.entity.Product;

import java.math.BigDecimal;

public class CartUtilities {

    public static BigDecimal getSubTotalForItem(Product product, Integer quantity) {
        return (product.getPrice()).multiply(BigDecimal.valueOf(quantity));
    }
}
