package com.solution.service.impl;

import com.solution.dao.FruitDao;
import com.solution.dto.FruitDto;
import com.solution.exceptions.FruitException;
import com.solution.model.Fruit;
import com.solution.service.FruitService;
import java.util.Map;
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

        FruitDto fruitDtoByFruit = fruitDao.getFruitDtoByFruit(firstExpiredFruit);
        checkQuantity(fruitDtoByFruit.getQuantity(), quantity);
        Long newQuantity = fruitDtoByFruit.getQuantity() - quantity;
        return fruitDao.save(fruitDtoByFruit.getFruit(), newQuantity);
    }

    @Override
    public FruitDto returnFruit(Fruit fruit, Long quantity) {
        if (!fruitDao.existed(fruit)) {
            throw new FruitException("No such fruit!");
        }
        FruitDto fruitDtoByFruit = fruitDao.getFruitDtoByFruit(fruit);
        Long newQuantity = fruitDtoByFruit.getQuantity() + quantity;
        return fruitDao.save(fruit, newQuantity);
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
