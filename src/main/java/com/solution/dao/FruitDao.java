package com.solution.dao;

import java.util.Map;
import com.solution.dto.FruitDto;
import com.solution.model.Fruit;
import org.springframework.stereotype.Repository;

@Repository
public interface FruitDao {
    Map<Fruit, Long> save(Fruit fruit, Long quantity);

    boolean existed(Fruit fruit);

    FruitDto getFruitDtoByFruit(Fruit fruit);

    Map<Fruit, Long> getAll();
}
