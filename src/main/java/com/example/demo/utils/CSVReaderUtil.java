package com.example.demo.utils;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;

import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class CSVReaderUtil {

  public static List<String[]> readAllLines(String filePath) throws Exception {
    Path path = Paths.get(ClassLoader.getSystemResource(filePath).toURI());
    try (Reader reader = Files.newBufferedReader(path)) {
      try (CSVReader csvReader = new CSVReaderBuilder(reader).withSkipLines(1).build()) {
        return csvReader.readAll();
      }
    }
  }
}
