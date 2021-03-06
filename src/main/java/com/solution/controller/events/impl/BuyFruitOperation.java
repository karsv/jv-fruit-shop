package com.solution.controller.events.impl;

import com.solution.controller.events.FruitOperation;
import com.solution.dto.FruitDto;
import com.solution.exceptions.FruitEventException;
import com.solution.service.FruitService;
import org.springframework.stereotype.Component;

@Component("b")
public class BuyFruitOperation implements FruitOperation {
    private final FruitService fruitService;

    public BuyFruitOperation(FruitService fruitService) {
        this.fruitService = fruitService;
    }

    @Override
    public FruitDto execute(FruitDto fruit) throws FruitEventException {
        return fruitService.buy(fruit.getFruit(), fruit.getQuantity());
    }
}
