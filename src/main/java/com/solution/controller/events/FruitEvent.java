package com.solution.controller.events;

import com.solution.dto.FruitDto;
import com.solution.exceptions.FruitEventException;

public interface FruitEvent {
    FruitDto execute() throws FruitEventException;
}
