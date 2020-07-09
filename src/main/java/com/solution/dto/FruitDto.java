package com.solution.dto;

import com.solution.model.Fruit;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FruitDto {
    private Fruit fruit;
    private Long quantity;

}
