package com.solution.config;

import com.solution.controller.events.FruitOperation;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {"com.solution"})
public class BeanConfig {
    private final List<FruitOperation> list;

    public BeanConfig(List<FruitOperation> list) {
        this.list = list;
    }

    @Bean
    public Map<String, FruitOperation> getFruitOperations() {
        Map<String, FruitOperation> fruitOperation = new HashMap<>();
        for (FruitOperation fo : list) {
            fruitOperation.put(fo.getOperation(), fo);
        }
        return fruitOperation;
    }
}
