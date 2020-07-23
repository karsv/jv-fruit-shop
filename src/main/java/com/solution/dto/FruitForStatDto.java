package com.solution.dto;

import java.util.Objects;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FruitForStatDto {
    private String name;
    private String date;
    private String quantity;

    @Override
    public String toString() {
        return name + ", " + date + ", " + quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        FruitForStatDto that = (FruitForStatDto) o;
        return Objects.equals(name, that.name)
                && Objects.equals(date, that.date)
                && Objects.equals(quantity, that.quantity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, date, quantity);
    }
}
