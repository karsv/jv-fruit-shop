package com.solution.controller;

import com.solution.controller.events.FruitEvent;
import com.solution.dto.FruitCsvDto;
import com.solution.dto.FruitDto;
import com.solution.factory.FruitFactory;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class EventController {
    private final FruitFactory fruitFactory;

    private final FruitEvent buyEvent;
    private final FruitEvent returnEvent;
    private final FruitEvent supplyEvent;

    private Map<String, FruitEvent> events;

    public EventController(FruitFactory fruitFactory,
                           @Qualifier("buyFruitEvent") FruitEvent buyEvent,
                           @Qualifier("returnFruitEvent") FruitEvent returnEvent,
                           @Qualifier("supplyFruitEvent") FruitEvent supplyEvent) {
        this.fruitFactory = fruitFactory;
        this.buyEvent = buyEvent;
        this.returnEvent = returnEvent;
        this.supplyEvent = supplyEvent;
    }

    @PostConstruct
    private void init() {
        events = new HashMap();
        events.put("s", supplyEvent);
        events.put("b", buyEvent);
        events.put("r", returnEvent);
    }

    public FruitDto executeEvent(FruitCsvDto fruit) {
        FruitEvent fruitEvent = events.get(fruit.getType());
        FruitDto fruitDto = fruitFactory.createFruit(fruit);
        return fruitEvent.execute(fruitDto);
    }
}
