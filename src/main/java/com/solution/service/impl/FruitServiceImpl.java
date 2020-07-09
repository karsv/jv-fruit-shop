package com.solution.service.impl;

import java.util.Map;
import com.solution.dao.FruitDao;
import com.solution.model.Fruit;
import com.solution.service.FruitService;
import org.springframework.stereotype.Service;

@Service
public class FruitServiceImpl implements FruitService {
    private final FruitDao fruitDao;

    public FruitServiceImpl(FruitDao fruitDao) {
        this.fruitDao = fruitDao;
    }

    @Override
    public Map<Fruit, Long> supply(Fruit fruit, Long quantity) {
        return fruitDao.save(fruit, quantity);
    }

    @Override
    public Map<Fruit, Long> buy(Fruit fruit, Long quantity) {
        return null;
    }

    @Override
    public Map<Fruit, Long> returnFruit(Fruit fruit, Long quantity) {
        return null;
    }
}
