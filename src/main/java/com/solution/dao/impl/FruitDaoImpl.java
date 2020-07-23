package com.solution.dao.impl;

import com.solution.dao.FruitDao;
import com.solution.dto.FruitDto;
import com.solution.exceptions.FruitException;
import com.solution.model.Fruit;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Map;
import java.util.Set;
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
    public Fruit getFirstExpiredFruit(Fruit fruit) {
        ArrayList<Fruit> list = new ArrayList();
        Set<Fruit> fruitNames = this.fruits.keySet();

        fruitNames.stream()
                .filter(f -> f.getFruit().equals(fruit.getFruit()))
                .forEach(list::add);

        if (list.size() == 0) {
            throw new FruitException("There isn't fruit with such name!");
        }

        list.sort((Fruit f1, Fruit f2) -> Comparator.comparing(Fruit::getDate).compare(f1, f2));

        return list.stream()
                .filter(f -> checkExpiring(f.getDate(), fruit.getDate()))
                .findFirst()
                .orElseThrow(() -> new FruitException("All such fruits are expired!"));
    }

    @Override
    public Map<Fruit, Long> getAll() {
        return fruits;
    }

    private boolean checkExpiring(LocalDate controlDate, LocalDate checkedDate) {
        return !checkedDate.isAfter(controlDate);
    }
}
