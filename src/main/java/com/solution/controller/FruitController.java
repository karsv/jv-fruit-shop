package com.solution.controller;

import java.io.IOException;
import java.util.List;
import com.solution.dto.FruitCsvDto;
import com.solution.service.FruitService;
import com.solution.util.CsvReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

@Component
public class FruitController {
    private final static Logger logger = LogManager.getLogger(FruitController.class);
    private final CsvReader csvReader;

    public FruitController(CsvReader csvReader) {
        this.csvReader = csvReader;
    }

    public void run(String path){
        try {
            List<FruitCsvDto> fruitCsvDtos = csvReader.readFile(path);
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
    }
}
