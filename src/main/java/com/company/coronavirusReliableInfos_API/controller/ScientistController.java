package com.company.coronavirusReliableInfos_API.controller;

import com.company.coronavirusReliableInfos_API.exceptions.ResourceNotFoundException;
import com.company.coronavirusReliableInfos_API.model.Scientist;
import com.company.coronavirusReliableInfos_API.repository.ScientistRepository;
import org.hibernate.ResourceClosedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/")
public class ScientistController {

    @Autowired
    private ScientistRepository scientistRepository;

    // get scientist
    @GetMapping("scientists")
    public List<Scientist> getAllScientists() {
        return this.scientistRepository.findAll();
    }

    // get scientist by id
    @GetMapping("scientists/{id}")
    public ResponseEntity<Scientist> getScientistById(@PathVariable(value = "id") Long scientistId) throws ResourceNotFoundException {
        Scientist scientist = scientistRepository.findById(scientistId).orElseThrow( () -> new ResourceNotFoundException("Scientist not found for this id = " + scientistId));
        return ResponseEntity.ok().body(scientist);
    }
    // save scientist
    // update scientist
    // delete scientist


}
