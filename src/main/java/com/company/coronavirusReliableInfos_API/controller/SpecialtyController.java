package com.company.coronavirusReliableInfos_API.controller;

import com.company.coronavirusReliableInfos_API.configuration.LoggerConfiguration;
import com.company.coronavirusReliableInfos_API.exceptions.ResourceNotFoundException;
import com.company.coronavirusReliableInfos_API.model.Specialty;
import com.company.coronavirusReliableInfos_API.repository.SpecialtyRepository;
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
public class SpecialtyController {

    private static final Logger log = Logger.getLogger(SpecialtyController.class.getName());

    @Autowired
    private SpecialtyRepository specialtyRepository;

    // GET all specialties
    @GetMapping("/specialties")
    public List<Specialty> getAllSpecialties() {
        String messageToLog = String.format("%s, GET all specialties invoked", LoggerConfiguration.dtf.format(LocalDateTime.now()));
        log.info(messageToLog);

        return this.specialtyRepository.findAll();
    }

    // GET specialty by id
    @GetMapping("/specialties/{id}")
    public ResponseEntity<Specialty> getSpecialtyById(@PathVariable(value = "id") Long specialtyId) throws ResourceNotFoundException {
        Specialty specialty = specialtyRepository.findById(specialtyId).orElseThrow(() -> new ResourceNotFoundException("Specialty not found for this id = " + specialtyId));

        String messageToLog = String.format("%s, GET specialty by id invoked", LoggerConfiguration.dtf.format(LocalDateTime.now()));
        log.info(messageToLog);

        return ResponseEntity.ok().body(specialty);
    }

    // SAVE specialty
    @PostMapping(path = "/specialties", consumes = "application/json")
    public Specialty createSpecialty(@Valid @RequestBody Specialty specialty) {
        String messageToLog = String.format("%s, SAVE specialty invoked", LoggerConfiguration.dtf.format(LocalDateTime.now()));
        log.info(messageToLog);

        return this.specialtyRepository.save(specialty);
    }

    // UPDATE specialty
    @PutMapping("/specialties/{id}")
    public ResponseEntity<Specialty> updateSpecialty(@PathVariable(value = "id") Long specialtyId, @Valid @RequestBody Specialty specialtyDetails) throws ResourceNotFoundException {
        Specialty specialty = specialtyRepository.findById(specialtyId).orElseThrow( () ->
                new ResourceNotFoundException("Specialty not found for this id = " + specialtyId));

        specialty.setName(specialtyDetails.getName());

        String messageToLog = String.format("%s, UPDATE specialty invoked", LoggerConfiguration.dtf.format(LocalDateTime.now()));
        log.info(messageToLog);

        return ResponseEntity.ok(this.specialtyRepository.save(specialty));
    }

    // DELETE specialty
    @DeleteMapping("/specialties/{id}")
    public Map<String, Boolean> deleteSpecialty(@PathVariable (value = "id") Long specialtyId) throws ResourceNotFoundException {
        Specialty specialty = specialtyRepository.findById(specialtyId).orElseThrow( () ->
                new ResourceNotFoundException("Specialty not found for this id = " + specialtyId));

        this.specialtyRepository.delete(specialty);

        String messageToLog = String.format("%s, DELETE specialty invoked", LoggerConfiguration.dtf.format(LocalDateTime.now()));
        log.info(messageToLog);

        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}
