package com.solution.factory;

import java.time.LocalDate;
import com.solution.dto.FruitCsvDto;
import com.solution.model.Fruit;
import org.springframework.stereotype.Component;

@Component
public class GoodFactory {
    public Fruit crateFruit(FruitCsvDto fruitDto) {
        Fruit fruit = new Fruit();
        fruit.setFruit(fruitDto.getFruit());
        fruit.setDate(LocalDate.parse(fruitDto.getDate()));
        return fruit;
    }
}
