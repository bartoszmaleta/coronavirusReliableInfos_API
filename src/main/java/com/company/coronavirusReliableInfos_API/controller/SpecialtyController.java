package com.company.coronavirusReliableInfos_API.controller;

import com.company.coronavirusReliableInfos_API.exceptions.ResourceNotFoundException;
import com.company.coronavirusReliableInfos_API.model.Specialty;
import com.company.coronavirusReliableInfos_API.repository.SpecialtyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class SpecialtyController {

    @Autowired
    private SpecialtyRepository specialtyRepository;

    // GET all specialties
    @GetMapping("/specialties")
    public List<Specialty> getAllSpecialties() {
        return this.specialtyRepository.findAll();
    }

    // GET specialty by id
    @GetMapping("/specialties/{id}")
    public ResponseEntity<Specialty> getCategoryById(@PathVariable(value = "id") Long specialtyId) throws ResourceNotFoundException {
        Specialty specialty = specialtyRepository.findById(specialtyId).orElseThrow(() -> new ResourceNotFoundException("Specialty not found for this id = " + specialtyId));
        return ResponseEntity.ok().body(specialty);
    }

    // SAVE specialty
    @PostMapping(path = "/specialties", consumes = "application/json")
    public Specialty createSpecialty(@Valid @RequestBody Specialty specialty) {
        return this.specialtyRepository.save(specialty);
    }

    // UPDATE specialty
    @PutMapping("/specialties/{id}")
    public ResponseEntity<Specialty> updateSpecialty(@PathVariable(value = "id") Long specialtyId, @Valid @RequestBody Specialty specialtyDetails) throws ResourceNotFoundException {
        Specialty specialty = specialtyRepository.findById(specialtyId).orElseThrow( () ->
                new ResourceNotFoundException("Specialty not found for this id = " + specialtyId));

        specialty.setName(specialtyDetails.getName());

        return ResponseEntity.ok(this.specialtyRepository.save(specialty));
    }

    // DELETE specialty
    @DeleteMapping("/specialties/{id}")
    public Map<String, Boolean> deleteSpecialty(@PathVariable (value = "id") Long specialtyId) throws ResourceNotFoundException {
        Specialty specialty = specialtyRepository.findById(specialtyId).orElseThrow( () ->
                new ResourceNotFoundException("Specialty not found for this id = " + specialtyId));

        this.specialtyRepository.delete(specialty);

        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}
