package com.company.coronavirusReliableInfos_API.controller;

import com.company.coronavirusReliableInfos_API.exceptions.ResourceNotFoundException;
import com.company.coronavirusReliableInfos_API.model.Journal;
import com.company.coronavirusReliableInfos_API.repository.JournalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class JournalController {

    @Autowired
    private JournalRepository journalRepository;

    // GET all journals
    @GetMapping("/journals")
    public List<Journal> getAllJournals() {
        return this.journalRepository.findAll();
    }

    // GET journal by id
    @GetMapping("/journals/{id}")
    public ResponseEntity<Journal> getJournalById(@PathVariable(value = "id") Long journalId) throws ResourceNotFoundException {
        Journal journal = journalRepository.findById(journalId).orElseThrow(() -> new ResourceNotFoundException("Journal not found for this id = " + journalId));
        return ResponseEntity.ok().body(journal);
    }

    // SAVE journal
    @PostMapping(path = "/journals", consumes = "application/json")
    public Journal createJournal(@Valid @RequestBody Journal journal) {
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

        return ResponseEntity.ok(this.journalRepository.save(journal));
    }

    // DELETE journal
    @DeleteMapping("journals/{id}")
    public Map<String, Boolean> deleteJournal(@PathVariable (value = "id") Long journalId) throws ResourceNotFoundException {
        Journal journal = journalRepository.findById(journalId).orElseThrow( () ->
                new ResourceNotFoundException("Journal not found for this id = " + journalId));

        this.journalRepository.delete(journal);

        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}
