package com.productrecommendationservice.repository;

import com.productrecommendationservice.entity.Recommendation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecommendationRepository extends JpaRepository<Recommendation, Long> {

    @Query("select r from Recommendation r where r.product.productName = :productName")
    List<Recommendation> findAllRecommendationByProductName(@Param("productName") String productName);
}
