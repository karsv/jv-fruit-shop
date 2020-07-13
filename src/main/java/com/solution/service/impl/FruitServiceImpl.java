package com.solution.service.impl;

import java.util.Map;
import com.solution.dao.FruitDao;
import com.solution.dto.FruitDto;
import com.solution.exceptions.FruitException;
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
    public FruitDto supply(Fruit fruit, Long quantity) {
        if (fruitDao.existed(fruit)) {
            FruitDto fruitDtoByFruit = fruitDao.getFruitDtoByFruit(fruit);
            Long newQuantity = fruitDtoByFruit.getQuantity() + quantity;
            fruitDao.save(fruit, newQuantity);
        }
        return fruitDao.save(fruit, quantity);
    }

    @Override
    public FruitDto buy(Fruit fruit, Long quantity) {
        Fruit firstExpiredFruit = fruitDao.getFirstExpiredFruit(fruit);

        if (fruitDao.existed(firstExpiredFruit)) {
            FruitDto fruitDtoByFruit = fruitDao.getFruitDtoByFruit(firstExpiredFruit);
            checkQuantity(fruitDtoByFruit.getQuantity(), quantity);
            Long newQuantity = fruitDtoByFruit.getQuantity() - quantity;
            return fruitDao.save(fruitDtoByFruit.getFruit(), newQuantity);
        }
        throw new FruitException("No such fruit!");
    }

    @Override
    public FruitDto returnFruit(Fruit fruit, Long quantity) {
        if (fruitDao.existed(fruit)) {
            FruitDto fruitDtoByFruit = fruitDao.getFruitDtoByFruit(fruit);
            Long newQuantity = fruitDtoByFruit.getQuantity() + quantity;
            return fruitDao.save(fruit, newQuantity);
        }
        return fruitDao.save(fruit, quantity);
    }

    @Override
    public FruitDto getFruit(Fruit fruit) {
        if (fruitDao.existed(fruit)) {
            return fruitDao.getFruitDtoByFruit(fruit);
        }
        throw new FruitException("No such fruit!");
    }

    @Override
    public Map<Fruit, Long> getAll() {
        return fruitDao.getAll();
    }

    private boolean checkQuantity(Long controlQuantity, Long checkedQuantity) {
        if (checkedQuantity > controlQuantity) {
            throw new FruitException("Not enough fruits!");
        }
        return true;
    }
}
