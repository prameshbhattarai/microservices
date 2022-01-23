package com.orderservice.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "products")
@Getter
@Setter
@NoArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column (name = "product_name", nullable = false)
    private String productName;

    @Column (name = "price", nullable = false)
    private BigDecimal price;

    @OneToMany (mappedBy = "product", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Item> items;
}
