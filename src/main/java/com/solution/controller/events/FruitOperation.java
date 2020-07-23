package com.solution.controller.events;

import com.solution.dto.FruitDto;
import com.solution.exceptions.FruitEventException;

public interface FruitOperation {
    FruitDto execute(FruitDto fruit) throws FruitEventException;

    String getOperation();
}
