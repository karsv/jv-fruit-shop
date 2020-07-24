package com.solution.controller;

import com.solution.controller.events.FruitOperation;
import com.solution.dto.FruitCsvDto;
import com.solution.dto.FruitDto;
import com.solution.factory.FruitFactory;
import java.util.Map;
import org.springframework.stereotype.Component;

@Component
public class EventController {
    private final FruitFactory fruitFactory;
    private final Map<String, FruitOperation> fruitOperations;

    public EventController(FruitFactory fruitFactory,
                           Map<String, FruitOperation> fruitOperations) {
        this.fruitFactory = fruitFactory;
        this.fruitOperations = fruitOperations;
    }

    public FruitDto executeEvent(FruitCsvDto fruit) {
        FruitOperation fruitEvent = fruitOperations.get(fruit.getType());
        FruitDto fruitDto = fruitFactory.createFruit(fruit);
        return fruitEvent.execute(fruitDto);
    }
}
