package com.solution.controller;

import java.util.HashMap;
import java.util.Map;
import javax.annotation.PostConstruct;
import com.solution.controller.events.FruitEvent;
import com.solution.dto.FruitCsvDto;
import com.solution.dto.FruitDto;
import com.solution.factory.FruitFactory;
import com.solution.model.Fruit;
import org.springframework.stereotype.Component;

@Component
public class EventController {
    private final FruitFactory fruitFactory;

    private Map<String, FruitEvent> events;

    public EventController(FruitFactory fruitFactory) {
        this.fruitFactory = fruitFactory;
    }

    @PostConstruct
    private void init() {
        events = new HashMap();
    }

    public FruitDto executeEvent(FruitCsvDto fruit) {
        FruitEvent fruitEvent = events.get(fruit.getType());
        FruitDto fruitDto = fruitFactory.createFruit(fruit);
        return fruitEvent.execute(fruitDto);
    }
}
