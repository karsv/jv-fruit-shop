package com.solution.service.impl;

import java.time.LocalDate;
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
    public Map<Fruit, Long> supply(Fruit fruit, Long quantity) {
        if (fruitDao.existed(fruit)) {
            FruitDto fruitDtoByFruit = fruitDao.getFruitDtoByFruit(fruit);
            Long newQuantity = fruitDtoByFruit.getQuantity() + quantity;
            fruitDao.save(fruit, newQuantity);
        }
        return fruitDao.save(fruit, quantity);
    }

    @Override
    public Map<Fruit, Long> buy(Fruit fruit, Long quantity) {
        if (fruitDao.existed(fruit)) {
            FruitDto fruitDtoByFruit = fruitDao.getFruitDtoByFruit(fruit);
            checkQuantity(fruitDtoByFruit.getQuantity(), quantity);
            checkExpiring(fruitDtoByFruit.getFruit().getDate(), fruit.getDate());
            Long newQuantity = fruitDtoByFruit.getQuantity() - quantity;
            fruitDao.save(fruitDtoByFruit.getFruit(), newQuantity);
        }
        throw new FruitException("No such fruit!");
    }

    @Override
    public Map<Fruit, Long> returnFruit(Fruit fruit, Long quantity) {
        if (fruitDao.existed(fruit)) {
            FruitDto fruitDtoByFruit = fruitDao.getFruitDtoByFruit(fruit);
            Long newQuantity = fruitDtoByFruit.getQuantity() + quantity;
            fruitDao.save(fruit, newQuantity);
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

    private boolean checkQuantity(Long controlQuantity, Long checkedQuantity) {
        if (checkedQuantity > controlQuantity) {
            throw new FruitException("Not enough fruits!");
        }
        return true;
    }

    private boolean checkExpiring(LocalDate controlDate, LocalDate checkedDate) {
        if (checkedDate.isAfter(controlDate)) {
            throw new FruitException("The fruit expired!");
        }
        return true;
    }
}
