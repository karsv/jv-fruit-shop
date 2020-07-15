package com.solution.repository;

import com.solution.model.Fruit;
import java.util.HashMap;
import java.util.Map;
import org.springframework.context.annotation.Bean;

public class FruitRepository {
    private final Map<Fruit, Long> fruits = new HashMap();

    @Bean
    public Map<Fruit, Long> getFruits() {
        return fruits;
    }
}
