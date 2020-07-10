package com.solution.controller.events.impl;

import com.solution.controller.events.FruitEvent;
import com.solution.dto.FruitDto;
import com.solution.exceptions.FruitEventException;
import com.solution.service.FruitService;
import org.springframework.stereotype.Component;

@Component
public class SupplyFruitEvent implements FruitEvent {
    private final FruitService fruitService;

    public SupplyFruitEvent(FruitService fruitService) {
        this.fruitService = fruitService;
    }

    @Override
    public FruitDto execute(FruitDto fruitDto) throws FruitEventException {
        return fruitService.supply(fruitDto.getFruit(), fruitDto.getQuantity());
    }
}
