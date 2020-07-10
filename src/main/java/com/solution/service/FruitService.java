package com.solution.service;

import java.util.Map;
import com.solution.dto.FruitDto;
import com.solution.model.Fruit;

public interface FruitService {
    Map<Fruit, Long> supply(Fruit fruit, Long quantity);

    Map<Fruit, Long> buy(Fruit fruit, Long quantity);

    Map<Fruit, Long> returnFruit(Fruit fruit, Long quantity);

    FruitDto getFruit(Fruit fruit);
}
