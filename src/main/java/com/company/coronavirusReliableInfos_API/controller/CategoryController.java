package com.company.coronavirusReliableInfos_API.controller;

import com.company.coronavirusReliableInfos_API.LoggerConfiguration;
import com.company.coronavirusReliableInfos_API.exceptions.ResourceNotFoundException;
import com.company.coronavirusReliableInfos_API.model.Category;
import com.company.coronavirusReliableInfos_API.repository.CategoryRepository;
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
public class CategoryController {
    private static final Logger log = Logger.getLogger(CategoryController.class.getName());

    @Autowired
    private CategoryRepository categoryRepository;

    // GET all categories
    @GetMapping("/categories")
    public List<Category> getAllCategories() {
        String messageToLog = String.format("%s, GET all categories invoked", LoggerConfiguration.dtf.format(LocalDateTime.now()));
        log.info(messageToLog);
        return this.categoryRepository.findAll();
    }

    // GET category by id
    @GetMapping("/categories/{id}")
    public ResponseEntity<Category> getCategoryById(@PathVariable(value = "id") Long categoryId) throws ResourceNotFoundException {
        Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category not found for this id = " + categoryId));

        String messageToLog = String.format("%s, GET category by id invoked", LoggerConfiguration.dtf.format(LocalDateTime.now()));
        log.info(messageToLog);

        return ResponseEntity.ok().body(category);
    }

    // SAVE category
    @PostMapping(path = "/categories", consumes = "application/json")
    public Category createCategory(@Valid @RequestBody Category category) {
        String messageToLog = String.format("%s, SAVE category invoked", LoggerConfiguration.dtf.format(LocalDateTime.now()));
        log.info(messageToLog);

        return this.categoryRepository.save(category);
    }

    // UPDATE category
    @PutMapping("/categories/{id}")
    public ResponseEntity<Category> updateCategory(@PathVariable(value = "id") Long categoryId, @Valid @RequestBody Category categoryDetails) throws ResourceNotFoundException {
        Category category = categoryRepository.findById(categoryId).orElseThrow( () ->
                new ResourceNotFoundException("Category not found for this id = " + categoryId));

        category.setName(categoryDetails.getName());
        category.setArticles(categoryDetails.getArticles());

        String messageToLog = String.format("%s, UPDATE category invoked", LoggerConfiguration.dtf.format(LocalDateTime.now()));
        log.info(messageToLog);

        return ResponseEntity.ok(this.categoryRepository.save(category));
    }

    // DELETE category
    @DeleteMapping("/categories/{id}")
    public Map<String, Boolean> deleteCategory(@PathVariable (value = "id") Long categoryId) throws ResourceNotFoundException {
        Category category = categoryRepository.findById(categoryId).orElseThrow( () ->
                new ResourceNotFoundException("Category not found for this id = " + categoryId));

        this.categoryRepository.delete(category);

        String messageToLog = String.format("%s, DELETE category invoked", LoggerConfiguration.dtf.format(LocalDateTime.now()));
        log.info(messageToLog);

        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}
