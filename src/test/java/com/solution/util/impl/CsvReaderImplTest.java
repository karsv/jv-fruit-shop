package com.solution.util.impl;

import com.solution.config.AppConfig;
import com.solution.dto.FruitCsvDto;
import com.solution.dto.FruitForStatDto;
import com.solution.util.CsvReader;
import java.io.IOException;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest(classes = AppConfig.class)
class CsvReaderImplTest {
    private static final String PATH_TO_TEST_CSV =
            "src/test/java/com/solution/testResources/test.csv";
    private static final String PATH_TO_MYTH_TEST_CSV =
            "src/test/java/com/solution/testResources/testNotFound.csv";
    private static final String PATH_TO_RESULT_CSV =
            "src/test/java/com/solution/testResources/result.csv";
    private static final String PATH_TO_NOT_EXISTED_RESULT_CSV =
            "src/test/java/com/solution/testResourcesNotExisted/testNotFound.csv";

    @Autowired
    private CsvReader csvReader;

    @Test
    void readFile() throws IOException {
        ArrayList<FruitCsvDto> list = new ArrayList();
        FruitCsvDto dto1 = new FruitCsvDto();
        dto1.setType("s");
        dto1.setFruit("banana");
        dto1.setDate("2020-10-17");
        dto1.setQuantity("100");
        FruitCsvDto dto2 = new FruitCsvDto();
        dto2.setType("b");
        dto2.setFruit("banana");
        dto2.setDate("2020-10-15");
        dto2.setQuantity("13");
        FruitCsvDto dto3 = new FruitCsvDto();
        dto3.setType("r");
        dto3.setFruit("banana");
        dto3.setDate("2020-10-17");
        dto3.setQuantity("10");
        list.add(dto1);
        list.add(dto2);
        list.add(dto3);
        assertEquals(list,
                csvReader.readFile(PATH_TO_TEST_CSV));
        assertThrows(IOException.class,
                () -> csvReader.readFile(PATH_TO_MYTH_TEST_CSV));
    }

    @Test
    void writeFile() throws IOException {
        ArrayList<FruitForStatDto> list = new ArrayList();
        FruitForStatDto dto1 = new FruitForStatDto();
        dto1.setName("Vodka");
        dto1.setQuantity("3");
        dto1.setDate("2020-07-02");
        FruitForStatDto dto2 = new FruitForStatDto();
        dto2.setName("Apple");
        dto2.setQuantity("1");
        dto2.setDate("2020-07-02");
        list.add(dto1);
        list.add(dto2);
        assertEquals(true,
                csvReader.writeFile(list, PATH_TO_RESULT_CSV));
        assertThrows(IOException.class,
                () -> csvReader.writeFile(list, PATH_TO_NOT_EXISTED_RESULT_CSV));
    }
}
