package com.solution.dto;

import com.solution.model.Fruit;
import java.util.Objects;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FruitDto {
    private Fruit fruit;
    private Long quantity;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        FruitDto fruitDto = (FruitDto) o;
        return Objects.equals(fruit, fruitDto.fruit)
                && Objects.equals(quantity, fruitDto.quantity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fruit, quantity);
    }
}
