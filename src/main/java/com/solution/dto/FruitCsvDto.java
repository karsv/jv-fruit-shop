package com.solution.dto;

import java.util.Objects;
import lombok.Data;

@Data
public class FruitCsvDto {
    private String type;
    private String fruit;
    private String quantity;
    private String date;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        FruitCsvDto that = (FruitCsvDto) o;
        return Objects.equals(type, that.type)
                && Objects.equals(fruit, that.fruit)
                && Objects.equals(quantity, that.quantity)
                && Objects.equals(date, that.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, fruit, quantity, date);
    }
}
