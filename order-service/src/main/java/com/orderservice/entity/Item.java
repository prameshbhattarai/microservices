package com.orderservice.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "items")
@Getter
@Setter
@NoArgsConstructor
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;

    @Column (name = "quantity")
    private int quantity;

    @Column (name = "subtotal", nullable = false)
    private BigDecimal subTotal;

    @ManyToOne (cascade = CascadeType.ALL)
    @JoinColumn (name = "product_id")
    private Product product;

    @ManyToMany (mappedBy = "items")
    @JsonIgnore
    private List<Order> orders;

    public Item(int quantity, Product product, BigDecimal subTotal) {
        this.quantity = quantity;
        this.product = product;
        this.subTotal = subTotal;
    }
}
