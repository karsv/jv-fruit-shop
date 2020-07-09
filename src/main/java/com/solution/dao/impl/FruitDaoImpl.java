package com.solution.dao.impl;

import java.util.Map;
import com.solution.dao.FruitDao;
import com.solution.model.Fruit;
import org.springframework.stereotype.Repository;

@Repository
public class FruitDaoImpl implements FruitDao {
    private final Map<Fruit, Long> fruits;

    public FruitDaoImpl(Map<Fruit, Long> fruits) {
        this.fruits = fruits;
    }

    @Override
    public Map<Fruit, Long> save(Fruit fruit, Long quantity) {
        fruits.put(fruit, quantity);
        return fruits;
    }

    @Override
    public Map<Fruit, Long> getAll() {
        return fruits;
    }
}
