package com.solution.controller;

import java.util.HashMap;
import java.util.Map;
import javax.annotation.PostConstruct;
import com.solution.controller.events.FruitEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class EventController {
    private Map<String, FruitEvent> events;

    @PostConstruct
    private void init(){
        events = new HashMap();

    }

    @Bean
    public Map<String, FruitEvent> getEvents(){
        return events;
    }
}
