package com.orderservice.entity;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "orders")
@Getter
@Setter
@NoArgsConstructor
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column (name = "ordered_date", columnDefinition = "DEFAULT CURRENT_TIMESTAMP")
    private LocalDate orderedDate;

    @Column(name = "status")
    private String status;

    @Column (name = "total")
    private BigDecimal total;

    @ManyToMany (cascade = CascadeType.ALL)
    @JoinTable (name = "cart" , joinColumns = @JoinColumn(name = "order_id"), inverseJoinColumns = @JoinColumn (name = "item_id"))
    private List<Item> items;

    @ManyToOne (cascade = CascadeType.ALL)
    @JoinColumn (name = "user_id")
    private User user;
}
