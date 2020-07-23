package com.solution.controller.events.impl;

import com.solution.controller.events.FruitOperation;
import com.solution.dto.FruitDto;
import com.solution.exceptions.FruitEventException;
import com.solution.service.FruitService;
import org.springframework.stereotype.Component;

@Component
public class ReturnFruitOperation implements FruitOperation {
    private final FruitService fruitService;
    private final String operation;

    public ReturnFruitOperation(FruitService fruitService) {
        this.fruitService = fruitService;
        this.operation = "r";
    }

    @Override
    public FruitDto execute(FruitDto fruitDto) throws FruitEventException {
        return fruitService.returnFruit(fruitDto.getFruit(), fruitDto.getQuantity());
    }

    @Override
    public String getOperation() {
        return this.operation;
    }
}
