package com.solution.service;

import java.util.Map;
import com.solution.dto.FruitDto;
import com.solution.model.Fruit;

public interface FruitService {
    FruitDto supply(Fruit fruit, Long quantity);

    FruitDto buy(Fruit fruit, Long quantity);

    FruitDto returnFruit(Fruit fruit, Long quantity);

    FruitDto getFruit(Fruit fruit);

    Map<Fruit, Long> getAll();
}
