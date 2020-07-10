package com.solution.controller.events.impl;

import com.solution.controller.events.FruitEvent;
import com.solution.exceptions.FruitEventException;
import org.springframework.stereotype.Component;

@Component
public class SupplyFruitEvent implements FruitEvent {
    @Override
    public void execute() throws FruitEventException {

    }
}
