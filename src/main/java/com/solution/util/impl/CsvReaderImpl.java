package com.solution.util.impl;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import com.solution.dto.FruitCsvDto;
import com.solution.model.Fruit;
import com.solution.util.CsvReader;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Component;

@Component
public class CsvReaderImpl implements CsvReader {
    private static final String[] HEADERS = {"type", "fruit", "quantity", "date"};

    @Override
    public List<FruitCsvDto> readFile(String path) throws IOException {
        Reader file = new FileReader(path);
        Iterable<CSVRecord> records = CSVFormat.DEFAULT
                .withHeader(HEADERS)
                .withFirstRecordAsHeader()
                .parse(file);

        List<FruitCsvDto> list = new ArrayList<>();
        FruitCsvDto csvFruitDto;

        for (CSVRecord record : records) {
            csvFruitDto = new FruitCsvDto();
            csvFruitDto.setType(record.get("type"));
            csvFruitDto.setFruit(record.get("fruit"));
            csvFruitDto.setQuantity(record.get("quantity"));
            csvFruitDto.setDate(record.get("date"));
            list.add(csvFruitDto);
        }
        return list;
    }

    @Override
    public boolean writeFile(List<Fruit> fruits) {
        return false;
    }
}
