package com.solution.util;

import java.io.IOException;
import java.util.List;
import com.solution.dto.FruitCsvDto;
import com.solution.dto.FruitForStatDto;

public interface CsvReader {
    List<FruitCsvDto> readFile(String path) throws IOException;

    boolean writeFile(List<FruitForStatDto> fruits, String path) throws IOException;
}
