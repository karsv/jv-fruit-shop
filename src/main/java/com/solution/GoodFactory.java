package com.solution;

import java.time.LocalDate;
import com.solution.dto.FruitDto;
import com.solution.model.Fruit;
import org.springframework.stereotype.Component;

@Component
public class GoodFactory {
    public Fruit crateFruit(FruitDto fruitDto) {
        Fruit fruit = new Fruit();
        fruit.setFruit(fruitDto.getFruit());
        fruit.setQuantity(Long.valueOf(fruitDto.getQuantity()));
        fruit.setDate(LocalDate.parse(fruitDto.getDate()));
        return fruit;
    }
}