package com.productrecommendationservice.controller;


import com.productrecommendationservice.entity.Product;
import com.productrecommendationservice.entity.Recommendation;
import com.productrecommendationservice.entity.User;
import com.productrecommendationservice.feignClient.ProductClient;
import com.productrecommendationservice.feignClient.UserClient;
import com.productrecommendationservice.service.RecommendationService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Log4j2
public class RecommendationController {

    private final RecommendationService recommendationService;
    private final ProductClient productClient;
    private final UserClient userClient;

    @Autowired
    public RecommendationController(RecommendationService recommendationService, ProductClient productClient, UserClient userClient) {
        this.recommendationService = recommendationService;
        this.productClient = productClient;
        this.userClient = userClient;
    }

    @GetMapping(value = "/recommendations", params = "name")
    private ResponseEntity<List<Recommendation>> getAllRating(@RequestParam("name") String productName) {
        List<Recommendation> recommendations = recommendationService.getAllRecommendationByProductName(productName);
        if (!recommendations.isEmpty()) {
            log.info("Found {} recommendation for product name: {}", recommendations.size(), productName);
            return new ResponseEntity<List<Recommendation>>(
                    recommendations,
                    HttpStatus.OK);
        }
        log.error("Unable to find any recommendation for product name: {}", productName);
        return new ResponseEntity<List<Recommendation>>(
                HttpStatus.NOT_FOUND);
    }

    @PostMapping(value = "/{userId}/recommendations/{productId}")
    private ResponseEntity<Recommendation> saveRecommendations(
            @PathVariable("userId") Long userId,
            @PathVariable("productId") Long productId,
            @RequestParam("rating") int rating) {

        Product product = productClient.getProductById(productId);
        User user = userClient.getUserById(userId);

        if (product != null && user != null) {
            try {
                Recommendation recommendation = new Recommendation();
                recommendation.setProduct(product);
                recommendation.setUser(user);
                recommendation.setRating(rating);
                recommendationService.saveRecommendation(recommendation);
                log.info("Successfully saved recommendation for product name: {} by user: {}", product.getProductName(), user.getUserName());
                return new ResponseEntity<Recommendation>(
                        recommendation,
                        HttpStatus.CREATED);
            } catch (Exception e) {
                log.error("Unable to save recommendation for userId: {}, productId: {} with rating: {}",
                        userId, productId, rating, e);
                e.printStackTrace();
                return new ResponseEntity<Recommendation>(
                        HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        if (product == null) log.error("Unable to find product by id: {}", productId);
        if (user == null) log.error("Unable to find user by id: {}", userId);
        return new ResponseEntity<Recommendation>(
                HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping(value = "/recommendations/{id}")
    private ResponseEntity<Void> deleteRecommendations(@PathVariable("id") Long id) {
        Recommendation recommendation = recommendationService.getRecommendationById(id);
        if (recommendation != null) {
            try {
                recommendationService.deleteRecommendation(id);
                log.info("Successfully deleted recommendation for productId: {}", id);
                return new ResponseEntity<Void>(
                        HttpStatus.OK);
            } catch (Exception e) {
                log.error("Unable to delete recommendation for id: {}", id, e);
                e.printStackTrace();
                return new ResponseEntity<Void>(
                        HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        return new ResponseEntity<Void>(
                HttpStatus.NOT_FOUND);
    }
}
