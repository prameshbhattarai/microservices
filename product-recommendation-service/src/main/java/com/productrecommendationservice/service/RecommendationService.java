package com.productrecommendationservice.service;

import com.productrecommendationservice.entity.Recommendation;

import java.util.List;

public interface RecommendationService {
    Recommendation getRecommendationById(Long recommendationId);
    Recommendation saveRecommendation(Recommendation recommendation);
    List<Recommendation> getAllRecommendationByProductName(String productName);
    void deleteRecommendation(Long id);
}
