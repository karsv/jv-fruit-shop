package com.solution.dao.impl;

import java.util.Map;
import com.solution.dao.FruitDao;
import com.solution.dto.FruitDto;
import com.solution.model.Fruit;
import org.springframework.stereotype.Repository;

@Repository
public class FruitDaoImpl implements FruitDao {
    private final Map<Fruit, Long> fruits;

    public FruitDaoImpl(Map<Fruit, Long> fruits) {
        this.fruits = fruits;
    }

    @Override
    public FruitDto save(Fruit fruit, Long quantity) {
        fruits.put(fruit, quantity);
        return getFruitDtoByFruit(fruit);
    }

    @Override
    public boolean existed(Fruit fruit) {
        return fruits.containsKey(fruit);
    }

    @Override
    public FruitDto getFruitDtoByFruit(Fruit fruit) {
        FruitDto fruitDto = new FruitDto();
        fruitDto.setFruit(fruit);
        fruitDto.setQuantity(fruits.get(fruit));
        return fruitDto;
    }

    @Override
    public Map<Fruit, Long> getAll() {
        return fruits;
    }
}
