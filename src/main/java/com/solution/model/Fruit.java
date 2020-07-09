package com.solution.model;

import java.time.LocalDate;
import java.util.Objects;
import lombok.Data;

@Data
public class Fruit implements Good {
    private String fruit;
    private LocalDate date;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Fruit)) return false;
        Fruit fruit1 = (Fruit) o;
        return getFruit().equals(fruit1.getFruit()) &&
                getDate().equals(fruit1.getDate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getFruit(), getDate());
    }
}
