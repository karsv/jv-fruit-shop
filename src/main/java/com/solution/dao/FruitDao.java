package com.solution.dao;

import com.solution.dto.FruitDto;
import com.solution.model.Fruit;
import java.util.Map;
import org.springframework.stereotype.Repository;

@Repository
public interface FruitDao {
    FruitDto save(Fruit fruit, Long quantity);

    boolean existed(Fruit fruit);

    FruitDto getFruitDtoByFruit(Fruit fruit);

    Fruit getFirstExpiredFruit(Fruit fruit);

    Map<Fruit, Long> getAll();
}
