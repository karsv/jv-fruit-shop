package com.solution.config;

import com.solution.controller.events.FruitOperation;
import java.util.List;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {"com.solution"})
public class BeanConfig {
    private final List<FruitOperation> list;

    public BeanConfig(List<FruitOperation> list) {
        this.list = list;
    }
}
