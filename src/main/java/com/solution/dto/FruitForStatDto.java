package com.solution.dto;

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
}
