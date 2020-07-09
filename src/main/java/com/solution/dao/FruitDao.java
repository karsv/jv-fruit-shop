package com.solution.dao;

import java.util.Map;
import java.util.Set;
import com.solution.model.Fruit;
import org.springframework.stereotype.Repository;

@Repository
public interface FruitDao {
    Map<Fruit, Long> save(Fruit fruit, Long quantity);

    Map<Fruit, Long> getAll();
}
