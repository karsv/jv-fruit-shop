package com.solution.controller;

import java.io.IOException;
import java.util.List;
import com.solution.dto.FruitCsvDto;
import com.solution.util.CsvReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

@Component
public class FruitController {
    private final static Logger logger = LogManager.getLogger(FruitController.class);
    private final CsvReader csvReader;
    private final EventController eventController;

    public FruitController(CsvReader csvReader,
                           EventController eventController) {
        this.csvReader = csvReader;
        this.eventController = eventController;
    }

    public void run(String path) {
        try {
            List<FruitCsvDto> fruitCsvDtos = csvReader.readFile(path);
            fruitCsvDtos.stream()
                    .forEach(e -> eventController.executeEvent(e));
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
    }
}
