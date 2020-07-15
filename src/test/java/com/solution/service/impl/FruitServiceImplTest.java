package com.solution.service.impl;

import com.solution.dao.FruitDao;
import com.solution.dao.impl.FruitDaoImpl;
import com.solution.dto.FruitDto;
import com.solution.exceptions.FruitException;
import com.solution.model.Fruit;
import com.solution.service.FruitService;
import java.time.LocalDate;
import java.util.HashMap;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

public class FruitServiceImplTest {
    private static final LocalDate SUPPLY_DATE = LocalDate.of(2020, 07, 15);
    private static final String SUPPLY_NEW_FRUIT = "New Fruit";
    private static final String SUPPLY_EXISTED_FRUIT = "Existed Fruit";
    private static final Long SUPPLY_NEW_QUANTITY = 10L;
    private static final Long SUPPLY_EXISTED_QUANTITY = 12L;
    private static final Long SUPPLY_NEW_QUANTITY_OF_EXISTED = 22L;

    private static final LocalDate BUY_EXPIRED_DATE = LocalDate.of(88, 4, 1);
    private static final LocalDate BUY_NOTEXISTED_FRUIT_DATE = LocalDate.of(2049, 5, 1);
    private static final LocalDate BUY_DATE_FRUIT = LocalDate.of(2020, 7, 21);
    private static final LocalDate BUY_EXISTED_FRUIT = LocalDate.of(2020, 8, 1);
    private static final String BUY_FRUIT = "Fruit";
    private static final String BUY_NOTEXISTED_FRUIT = "Not existed Fruit";
    private static final String BUY_EXPIRED_FRUIT = "Expired fruit";
    private static final Long BUY_QUANTITY = 1l;
    private static final Long BUY_EXISTED_QUANTITY = 10l;
    private static final Long BUY_NEW_EXISTED_QUANTITY = 9l;

    private static final String GETALL_FRUIT1 = "Fruit 1";
    private static final String GETALL_FRUIT2 = "Fruit 2";
    private static final LocalDate GETALL_DATE = LocalDate.of(2020, 7, 21);
    private static final Long GETALL_FRUIT_QUANTITY = 1L;

    private FruitDao fruitDao;
    private FruitService fruitService;


    @BeforeEach
    private void init() {
        fruitDao = Mockito.mock(FruitDaoImpl.class);
        fruitService = new FruitServiceImpl(fruitDao);
    }

    @Test
    void supply() {
        Fruit newFruit = new Fruit();
        newFruit.setDate(SUPPLY_DATE);
        newFruit.setFruit(SUPPLY_NEW_FRUIT);
        Long newSupplyFruitQuantity = SUPPLY_NEW_QUANTITY;
        FruitDto newFruitDto = new FruitDto();
        newFruitDto.setFruit(newFruit);
        newFruitDto.setQuantity(newSupplyFruitQuantity);

        when(fruitDao.existed(newFruit)).thenReturn(false);
        when(fruitDao.save(newFruit, newSupplyFruitQuantity)).thenReturn(newFruitDto);

        Fruit existedFruit = new Fruit();
        existedFruit.setDate(SUPPLY_DATE);
        existedFruit.setFruit(SUPPLY_EXISTED_FRUIT);
        Long newSupplyExistedFruitQuantity = SUPPLY_NEW_QUANTITY;

        FruitDto existedFruitDto = new FruitDto();
        Long existedFruitQuantity = SUPPLY_EXISTED_QUANTITY;
        existedFruitDto.setFruit(existedFruit);
        existedFruitDto.setQuantity(existedFruitQuantity);

        FruitDto newExistedFruitDto = new FruitDto();
        Long existedNewFruitQuantity = SUPPLY_NEW_QUANTITY_OF_EXISTED;
        newExistedFruitDto.setFruit(existedFruit);
        newExistedFruitDto.setQuantity(existedNewFruitQuantity);

        when(fruitDao.existed(existedFruit)).thenReturn(true);
        when(fruitDao.getFruitDtoByFruit(existedFruit)).
                thenReturn(existedFruitDto);
        when(fruitDao.save(existedFruit, newSupplyExistedFruitQuantity)).
                thenReturn(newExistedFruitDto);

        assertEquals(newFruitDto, fruitService.supply(newFruit, newSupplyFruitQuantity));
        assertEquals(newExistedFruitDto,
                fruitService.supply(existedFruit, newSupplyExistedFruitQuantity));

    }

    @Test
    void buy() {
        Fruit expiredFruit = new Fruit();
        expiredFruit.setFruit(BUY_EXPIRED_FRUIT);
        expiredFruit.setDate(BUY_EXPIRED_DATE);
        when(fruitDao.getFirstExpiredFruit(expiredFruit))
                .thenThrow(new FruitException("All such fruits are expired!"));

        Fruit notExistedFruit = new Fruit();
        notExistedFruit.setFruit(BUY_NOTEXISTED_FRUIT);
        notExistedFruit.setDate(BUY_NOTEXISTED_FRUIT_DATE);
        when(fruitDao.getFirstExpiredFruit(notExistedFruit))
                .thenThrow(new FruitException("There isn't fruit with such name!"));

        Fruit fruit = new Fruit();
        fruit.setFruit(BUY_FRUIT);
        fruit.setDate(BUY_DATE_FRUIT);
        Fruit existedFruit = new Fruit();
        existedFruit.setFruit(BUY_FRUIT);
        existedFruit.setDate(BUY_EXISTED_FRUIT);

        FruitDto fruitDto = new FruitDto();
        fruitDto.setFruit(existedFruit);
        fruitDto.setQuantity(BUY_EXISTED_QUANTITY);
        FruitDto newFruitDto = new FruitDto();
        newFruitDto.setFruit(existedFruit);
        newFruitDto.setQuantity(BUY_NEW_EXISTED_QUANTITY);
        when(fruitDao.getFirstExpiredFruit(fruit)).thenReturn(existedFruit);
        when(fruitDao.getFruitDtoByFruit(existedFruit)).thenReturn(fruitDto);
        when(fruitDao.save(existedFruit, 9L)).thenReturn(newFruitDto);

        assertThrows(FruitException.class, () -> fruitService.buy(expiredFruit, BUY_QUANTITY));
        assertThrows(FruitException.class, () -> fruitService.buy(notExistedFruit, BUY_QUANTITY));
        assertEquals(newFruitDto, fruitService.buy(fruit, BUY_QUANTITY));
    }

    @Test
    void returnFruit() {
        Fruit notExistedFruit = new Fruit();
        notExistedFruit.setFruit("Not existed fruit");
        notExistedFruit.setDate(LocalDate.of(2020, 7, 21));
        when(fruitDao.existed(notExistedFruit)).thenThrow(new FruitException("No such fruit!"));

        Fruit fruit = new Fruit();
        fruit.setFruit("Fruit");
        fruit.setDate(LocalDate.of(2020, 7, 21));

        FruitDto fruitExistedDto = new FruitDto();
        fruitExistedDto.setFruit(fruit);
        fruitExistedDto.setQuantity(10L);
        FruitDto fruitNewDto = new FruitDto();
        fruitNewDto.setFruit(fruit);
        fruitNewDto.setQuantity(12L);
        when(fruitDao.existed(fruit)).thenReturn(true);
        when(fruitDao.getFruitDtoByFruit(fruit)).thenReturn(fruitExistedDto);
        when(fruitDao.save(fruit, 12L)).thenReturn(fruitNewDto);

        assertThrows(FruitException.class, () -> fruitService.returnFruit(notExistedFruit, 2L));
        assertEquals(fruitNewDto, fruitService.returnFruit(fruit, 2L));

    }

    @Test
    void getAll() {
        HashMap<Fruit, Long> fruits = new HashMap();
        Fruit fruit1 = new Fruit();
        fruit1.setFruit(GETALL_FRUIT1);
        fruit1.setDate(GETALL_DATE);
        Fruit fruit2 = new Fruit();
        fruit2.setFruit(GETALL_FRUIT2);
        fruit2.setDate(GETALL_DATE);
        fruits.put(fruit1, GETALL_FRUIT_QUANTITY);
        fruits.put(fruit2, GETALL_FRUIT_QUANTITY);
        when(fruitDao.getAll()).thenReturn(fruits);

        assertEquals(fruits, fruitService.getAll());
    }
}
