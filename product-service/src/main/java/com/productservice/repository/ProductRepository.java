package com.productservice.repository;

import com.productservice.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("SELECT p FROM Product p where p.category = :category")
    List<Product> findAllByCategory(@Param("category") String category);

    @Query("SELECT p FROM Product p where p.productName = :productName")
    List<Product> findAllByProductName(@Param("productName") String productName);
}
