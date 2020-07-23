package com.solution.controller;

import com.solution.controller.events.FruitOperation;
import com.solution.dto.FruitCsvDto;
import com.solution.dto.FruitForStatDto;
import com.solution.model.Fruit;
import com.solution.service.FruitService;
import com.solution.util.CsvReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

@Component
public class FruitController {
    private static final Logger logger = LogManager.getLogger(FruitController.class);
    private final CsvReader csvReader;
    private final EventController eventController;
    private final FruitService fruitService;
    private final Map<String, FruitOperation> fruitOperations;

    public FruitController(CsvReader csvReader,
                           EventController eventController,
                           FruitService fruitService,
                           Map<String, FruitOperation> fruitOperations) {
        this.csvReader = csvReader;
        this.eventController = eventController;
        this.fruitService = fruitService;
        this.fruitOperations = fruitOperations;
    }

    public void run(String path) {
        try {
            List<FruitCsvDto> fruitCsvDtos = csvReader.readFile(path);
            fruitCsvDtos.stream()
                    .forEach(f -> {
                        try {
                            eventController.executeEvent(f);
                        } catch (RuntimeException e) {
                            return;
                        }
                    });
            csvReader.writeFile(convertFruitToStat(fruitService.getAll()),
                    path.substring(0, path.lastIndexOf('/')) + "/" + "result.csv");
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
    }

    private List<FruitForStatDto> convertFruitToStat(Map<Fruit, Long> fruits) {
        ArrayList<FruitForStatDto> list = new ArrayList();
        fruits.forEach((fruit, quantity) -> {
            FruitForStatDto dto = new FruitForStatDto();
            dto.setName(fruit.getFruit());
            dto.setDate(fruit.getDate().toString());
            dto.setQuantity(String.valueOf(quantity));
            list.add(dto);
        });
        return list;
    }
}
