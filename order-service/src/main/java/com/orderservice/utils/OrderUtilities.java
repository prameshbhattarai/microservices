package com.orderservice.utils;

import com.orderservice.entity.Item;

import java.math.BigDecimal;
import java.util.List;

public class OrderUtilities {

    public static BigDecimal countTotalPrice(List<Item> cart){
        BigDecimal total = BigDecimal.ZERO;
        for (Item item : cart) {
            total = total.add(item.getSubTotal());
        }
        return total;
    }
}
