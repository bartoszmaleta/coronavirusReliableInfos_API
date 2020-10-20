package com.company.coronavirusReliableInfos_API.controller;

import com.company.coronavirusReliableInfos_API.exceptions.ResourceNotFoundException;
import com.company.coronavirusReliableInfos_API.model.Scientist;
import com.company.coronavirusReliableInfos_API.repository.ScientistRepository;
import org.hibernate.ResourceClosedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    @PostMapping("scientists")
    public Scientist createScientist(@RequestBody Scientist scientist) {
        return this.scientistRepository.save(scientist);
    }

    // update scientist
    @PutMapping("scientists/{id}")
    public ResponseEntity<Scientist> updateScientist(@PathVariable(value = "id") Long scientistId, @Valid @RequestBody Scientist scientistDetails) throws ResourceNotFoundException {
        Scientist scientist = scientistRepository.findById(scientistId).orElseThrow( () -> new ResourceNotFoundException("Scientist not found for this id = " + scientistId));

        scientist.setFirstName(scientistDetails.getFirstName());
        scientist.setLastName(scientistDetails.getLastName());
        scientist.setLink(scientistDetails.getLink());
        scientist.setCountry(scientistDetails.getCountry());
        scientist.setRating(scientistDetails.getRating());

        return ResponseEntity.ok(this.scientistRepository.save(scientist));
    }

    // delete scientist
    @DeleteMapping("scientists/{id}")
    public Map<String, Boolean> deleteScientist(@PathVariable(value = "id") Long scientistId) throws ResourceNotFoundException {
        Scientist scientist = scientistRepository.findById(scientistId).orElseThrow( () -> new ResourceNotFoundException("Scientist not found for this id = " + scientistId));

        this.scientistRepository.delete(scientist);

        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }


}
