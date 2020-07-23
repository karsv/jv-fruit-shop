package com.solution.dao.impl;

import com.solution.dao.FruitDao;
import com.solution.dto.FruitDto;
import com.solution.exceptions.FruitException;
import com.solution.model.Fruit;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

class FruitDaoImplTest {
    private static final String FRUIT_NAME = "Fruit";
    private static final String NOT_EXISTED_FRUIT_NAME = "Hernia yakas nache yagoda";
    private static final Long QUANTITY = 1L;
    private static final LocalDate DATE = LocalDate.of(2020, 7, 15);
    private static final LocalDate BUYING_DATE = LocalDate.of(2020, 7, 12);
    private static final LocalDate ANOTHER_DATE = LocalDate.of(2020, 7, 21);
    private static final LocalDate expiredDate = LocalDate.of(2020, 7, 30);

    private Map<Fruit, Long> fruits;
    private FruitDao fruitDao;
    private Fruit fruit;
    private Fruit fruitToFind;
    private Fruit fruitAllExpired;
    private Fruit notExistedFruit;
    private FruitDto fruitDto;

    @BeforeEach
    void setUp() {
        fruits = Mockito.mock(Map.class);
        fruitDao = new FruitDaoImpl(fruits);

        fruit = new Fruit();
        fruit.setFruit(FRUIT_NAME);
        fruit.setDate(DATE);

        fruitToFind = new Fruit();
        fruitToFind.setFruit(FRUIT_NAME);
        fruitToFind.setDate(BUYING_DATE);

        fruitAllExpired = new Fruit();
        fruitAllExpired.setFruit(FRUIT_NAME);
        fruitAllExpired.setDate(expiredDate);

        notExistedFruit = new Fruit();
        notExistedFruit.setFruit(NOT_EXISTED_FRUIT_NAME);
        notExistedFruit.setDate(ANOTHER_DATE);

        fruitDto = new FruitDto();
        fruitDto.setFruit(fruit);
        fruitDto.setQuantity(QUANTITY);
    }

    @Test
    void save() {
        when(fruits.get(fruit)).thenReturn(QUANTITY);
        when(fruits.put(fruit, QUANTITY)).thenReturn(QUANTITY);
        assertEquals(fruitDto, fruitDao.save(fruit, QUANTITY));
    }

    @Test
    void existed() {
        when(fruits.containsKey(fruit)).thenReturn(true);
        when(fruits.containsKey(notExistedFruit)).thenReturn(false);
        assertEquals(true, fruitDao.existed(fruit));
        assertEquals(false, fruitDao.existed(notExistedFruit));
    }

    @Test
    void getFirstExpiredFruit() {
        Set<Fruit> set = new HashSet();
        set.add(fruit);
        set.add(notExistedFruit);

        HashSet emptyHashSet = new HashSet<>();
        when(fruits.keySet()).thenReturn(set);
        assertEquals(fruit, fruitDao.getFirstExpiredFruit(fruitToFind));
        when(fruits.keySet()).thenReturn(emptyHashSet);
        assertThrows(FruitException.class, () -> fruitDao.getFirstExpiredFruit(fruitToFind));
        when(fruits.keySet()).thenReturn(set);
        assertThrows(FruitException.class, () -> fruitDao.getFirstExpiredFruit(fruitAllExpired));
    }
}
