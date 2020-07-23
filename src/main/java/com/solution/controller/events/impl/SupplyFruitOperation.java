package com.solution.controller.events.impl;

import com.solution.controller.events.FruitOperation;
import com.solution.dto.FruitDto;
import com.solution.exceptions.FruitEventException;
import com.solution.service.FruitService;
import org.springframework.stereotype.Component;

@Component
public class SupplyFruitOperation implements FruitOperation {
    private final FruitService fruitService;

    private final String operation;

    public SupplyFruitOperation(FruitService fruitService) {
        this.fruitService = fruitService;
        this.operation = "s";
    }

    @Override
    public FruitDto execute(FruitDto fruitDto) throws FruitEventException {
        return fruitService.supply(fruitDto.getFruit(), fruitDto.getQuantity());
    }

    @Override
    public String getOperation() {
        return this.operation;
    }
}
