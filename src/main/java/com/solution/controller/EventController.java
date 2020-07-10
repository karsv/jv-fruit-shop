package com.solution.controller;

import java.util.HashMap;
import java.util.Map;
import javax.annotation.PostConstruct;
import com.solution.controller.events.FruitEvent;
import com.solution.dto.FruitCsvDto;
import com.solution.dto.FruitDto;
import org.springframework.stereotype.Component;

@Component
public class EventController {
    private Map<String, FruitEvent> events;

    @PostConstruct
    private void init() {
        events = new HashMap();
    }

    public FruitDto executeEvent(FruitCsvDto fruit) {
        FruitEvent fruitEvent = events.get(fruit.getType());
        return fruitEvent.execute();
    }
}
