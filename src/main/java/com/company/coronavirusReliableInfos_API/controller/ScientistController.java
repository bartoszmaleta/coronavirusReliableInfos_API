package com.company.coronavirusReliableInfos_API.controller;

import com.company.coronavirusReliableInfos_API.LoggerConfiguration;
import com.company.coronavirusReliableInfos_API.exceptions.ResourceNotFoundException;
import com.company.coronavirusReliableInfos_API.model.Scientist;
import com.company.coronavirusReliableInfos_API.repository.ScientistRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class ScientistController {

    private static final Logger log = Logger.getLogger(ScientistController.class.getName());

    @Autowired
    private ScientistRepository scientistRepository;

    // GET scientist
    @GetMapping("/scientists")
    public List<Scientist> getAllScientists() {
        String messageToLog = String.format("%s, GET all scientists invoked", LoggerConfiguration.dtf.format(LocalDateTime.now()));
        log.info(messageToLog);

        return this.scientistRepository.findAll();
    }

    // GET scientist by id
    @GetMapping("/scientists/{id}")
    public ResponseEntity<Scientist> getScientistById(@PathVariable(value = "id") Long scientistId) throws ResourceNotFoundException {
        Scientist scientist = scientistRepository.findById(scientistId).orElseThrow( () -> new ResourceNotFoundException("Scientist not found for this id = " + scientistId));

        String messageToLog = String.format("%s, GET scientist by id invoked", LoggerConfiguration.dtf.format(LocalDateTime.now()));
        log.info(messageToLog);

        return ResponseEntity.ok().body(scientist);
    }

    // SAVE scientist
    @PostMapping(path = "/scientists", consumes = "application/json")
    public Scientist createScientist(@Valid @RequestBody Scientist scientist) {
        String messageToLog = String.format("%s, SAVE scientist invoked", LoggerConfiguration.dtf.format(LocalDateTime.now()));
        log.info(messageToLog);

        return this.scientistRepository.save(scientist);
    }

    // UPDATE scientist
    @PutMapping("/scientists/{id}")
    public ResponseEntity<Scientist> updateScientist(@PathVariable(value = "id") Long scientistId, @Valid @RequestBody Scientist scientistDetails) throws ResourceNotFoundException {
        Scientist scientist = scientistRepository.findById(scientistId).orElseThrow( () -> new ResourceNotFoundException("Scientist not found for this id = " + scientistId));

        scientist.setFirstName(scientistDetails.getFirstName());
        scientist.setLastName(scientistDetails.getLastName());
        scientist.setLink(scientistDetails.getLink());
        scientist.setCountry(scientistDetails.getCountry());
        scientist.setRating(scientistDetails.getRating());
        scientist.setArticles(scientistDetails.getArticles());

        String messageToLog = String.format("%s, UPDATE scientist invoked", LoggerConfiguration.dtf.format(LocalDateTime.now()));
        log.info(messageToLog);

        return ResponseEntity.ok(this.scientistRepository.save(scientist));
    }

    // DELETE scientist
    @DeleteMapping("/scientists/{id}")
    public Map<String, Boolean> deleteScientist(@PathVariable(value = "id") Long scientistId) throws ResourceNotFoundException {
        Scientist scientist = scientistRepository.findById(scientistId).orElseThrow( () -> new ResourceNotFoundException("Scientist not found for this id = " + scientistId));

        this.scientistRepository.delete(scientist);

        String messageToLog = String.format("%s, DELETE scientist invoked", LoggerConfiguration.dtf.format(LocalDateTime.now()));
        log.info(messageToLog);

        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }


}
