package com.example.software_doc_design.contoller;

import com.example.software_doc_design.service.DatabasePersistenceService;
import com.example.software_doc_design.infrastructure.file.fileReader.CsvReader;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/fileReader")
public class FileReaderController {
    private final CsvReader csvReader;
    private final DatabasePersistenceService databasePersistenceService;


    public FileReaderController(CsvReader csvReader, DatabasePersistenceService databasePersistenceService) {
        this.csvReader = csvReader;
        this.databasePersistenceService = databasePersistenceService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Void> readCsvFile() {
        try {
            Map<String, List<String>> data = csvReader.readCsvFile("database.csv");
            databasePersistenceService.persistDatabaseData(data);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (FileNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
