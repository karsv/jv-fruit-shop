package com.solution.util;

import java.io.IOException;
import java.util.List;
import com.solution.dto.FruitDto;
import com.solution.model.Fruit;

public interface CsvReader {
    public List<FruitDto> readFile(String path) throws IOException;

    public boolean writeFile(List<Fruit> fruits);
}
