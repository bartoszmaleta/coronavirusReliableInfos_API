package com.company.coronavirusReliableInfos_API.controller;

import com.company.coronavirusReliableInfos_API.LoggerConfiguration;
import com.company.coronavirusReliableInfos_API.exceptions.ResourceNotFoundException;
import com.company.coronavirusReliableInfos_API.model.Journal;
import com.company.coronavirusReliableInfos_API.repository.JournalRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class JournalController {

    private static final Logger log = Logger.getLogger(JournalController.class.getName());

    @Autowired
    private JournalRepository journalRepository;

    // GET all journals
    @GetMapping("/journals")
    public List<Journal> getAllJournals() {
        String messageToLog = String.format("%s, GET all journals invoked", LoggerConfiguration.dtf.format(LocalDateTime.now()));
        log.info(messageToLog);

        return this.journalRepository.findAll();
    }

    // GET journal by id
    @GetMapping("/journals/{id}")
    public ResponseEntity<Journal> getJournalById(@PathVariable(value = "id") Long journalId) throws ResourceNotFoundException {
        Journal journal = journalRepository.findById(journalId).orElseThrow(() -> new ResourceNotFoundException("Journal not found for this id = " + journalId));

        String messageToLog = String.format("%s, GET journal by id invoked", LoggerConfiguration.dtf.format(LocalDateTime.now()));
        log.info(messageToLog);

        return ResponseEntity.ok().body(journal);
    }

    // SAVE journal
    @PostMapping(path = "/journals", consumes = "application/json")
    public Journal createJournal(@Valid @RequestBody Journal journal) {
        String messageToLog = String.format("%s, SAVE journal invoked", LoggerConfiguration.dtf.format(LocalDateTime.now()));
        log.info(messageToLog);

        return this.journalRepository.save(journal);
    }

    // UPDATE journal
    @PutMapping("/journals/{id}")
    public ResponseEntity<Journal> updateJournal(@PathVariable(value = "id") Long journalId, @Valid @RequestBody Journal journalDetails) throws ResourceNotFoundException {
        Journal journal = journalRepository.findById(journalId).orElseThrow( () ->
        new ResourceNotFoundException("Journal not found for this id = " + journalId));

        journal.setLink(journalDetails.getLink());
        journal.setName(journalDetails.getName());
        journal.setArticles(journalDetails.getArticles());

        String messageToLog = String.format("%s, UPDATE journal invoked", LoggerConfiguration.dtf.format(LocalDateTime.now()));
        log.info(messageToLog);

        return ResponseEntity.ok(this.journalRepository.save(journal));
    }

    // DELETE journal
    @DeleteMapping("/journals/{id}")
    public Map<String, Boolean> deleteJournal(@PathVariable (value = "id") Long journalId) throws ResourceNotFoundException {
        Journal journal = journalRepository.findById(journalId).orElseThrow( () ->
                new ResourceNotFoundException("Journal not found for this id = " + journalId));

        this.journalRepository.delete(journal);

        String messageToLog = String.format("%s, DELETE journal invoked", LoggerConfiguration.dtf.format(LocalDateTime.now()));
        log.info(messageToLog);

        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}
