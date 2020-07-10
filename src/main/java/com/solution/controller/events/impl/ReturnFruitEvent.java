package com.solution.controller.events.impl;

import com.solution.controller.events.FruitEvent;
import com.solution.dto.FruitDto;
import com.solution.exceptions.FruitEventException;
import org.springframework.stereotype.Component;

@Component
public class ReturnFruitEvent implements FruitEvent {
    @Override
    public FruitDto execute() throws FruitEventException {
        return null;
    }
}
