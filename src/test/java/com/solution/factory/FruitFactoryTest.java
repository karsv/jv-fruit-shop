package com.solution.factory;

import com.solution.dto.FruitCsvDto;
import com.solution.dto.FruitDto;
import com.solution.model.Fruit;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class FruitFactoryTest {
    private static final String FRUIT_NAME = "Fruit";
    private static final String OPERATION_TYPE = "s";
    private static final String FRUIT_QUANTITY = "1";
    private static final String FRUIT_DATE = "2020-07-15";
    private static final String FRUIT_WRONG_DATE = "2020-0b-15";
    private FruitFactory fruitFactory;

    @BeforeEach
    private void init() {
        fruitFactory = new FruitFactory();
    }

    @Test
    void createFruit() {
        FruitCsvDto fruitCsvDto = new FruitCsvDto();
        fruitCsvDto.setFruit(FRUIT_NAME);
        fruitCsvDto.setType(OPERATION_TYPE);
        fruitCsvDto.setQuantity(FRUIT_QUANTITY);
        fruitCsvDto.setDate(FRUIT_DATE);
        Fruit fruit = new Fruit();
        fruit.setFruit(fruitCsvDto.getFruit());
        fruit.setDate(LocalDate.parse(fruitCsvDto.getDate()));
        FruitDto fruitDto = new FruitDto();
        fruitDto.setFruit(fruit);
        fruitDto.setQuantity(Long.valueOf(fruitCsvDto.getQuantity()));
        assertEquals(fruitDto, fruitFactory.createFruit(fruitCsvDto));
        fruitCsvDto.setDate(FRUIT_WRONG_DATE);
        assertThrows(DateTimeParseException.class, () -> fruitFactory.createFruit(fruitCsvDto));
    }
}
