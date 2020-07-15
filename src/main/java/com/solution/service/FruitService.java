package com.solution.service;

import com.solution.dto.FruitDto;
import com.solution.model.Fruit;
import java.util.Map;

public interface FruitService {
    FruitDto supply(Fruit fruit, Long quantity);

    FruitDto buy(Fruit fruit, Long quantity);

    FruitDto returnFruit(Fruit fruit, Long quantity);

    Map<Fruit, Long> getAll();
}
