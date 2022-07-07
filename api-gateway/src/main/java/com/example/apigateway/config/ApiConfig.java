package com.example.apigateway.config;

import com.example.apigateway.filter.CustomSessionFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApiConfig {
    
    @Bean("sessionFilter")
    public CustomSessionFilter sessionFilter() {
        return new CustomSessionFilter();
    }
}
