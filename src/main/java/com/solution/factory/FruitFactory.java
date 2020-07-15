package com.solution.factory;

import com.solution.dto.FruitCsvDto;
import com.solution.dto.FruitDto;
import com.solution.model.Fruit;
import java.time.LocalDate;
import org.springframework.stereotype.Component;

@Component
public class FruitFactory {

    public FruitDto createFruit(FruitCsvDto fruitCsvDto) {
        Fruit fruit = new Fruit();
        fruit.setFruit(fruitCsvDto.getFruit());
        fruit.setDate(LocalDate.parse(fruitCsvDto.getDate()));
        FruitDto fruitDto = new FruitDto();
        fruitDto.setFruit(fruit);
        fruitDto.setQuantity(Long.valueOf(fruitCsvDto.getQuantity()));
        return fruitDto;
    }
}
