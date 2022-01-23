package com.orderservice.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.Jedis;

@Configuration
public class ApplicationConfig {

    @Bean(name = "jedish")
    public Jedis getJedish() {
        return new Jedis();
    }

    @Bean(name = "objectMapper")
    public ObjectMapper getObjectMapper() {
        return new ObjectMapper();
    }
}
