package com.company.coronavirusReliableInfos_API.controller;

import com.company.coronavirusReliableInfos_API.exceptions.ResourceNotFoundException;
import com.company.coronavirusReliableInfos_API.model.Category;
import com.company.coronavirusReliableInfos_API.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class CategoryController {

    @Autowired
    private CategoryRepository categoryRepository;

    // GET all categories
    @GetMapping("/categories")
    public List<Category> getAllCategories() {
        return this.categoryRepository.findAll();
    }

    // GET category by id
    @GetMapping("/categories/{id}")
    public ResponseEntity<Category> getCategoryById(@PathVariable(value = "id") Long categoryId) throws ResourceNotFoundException {
        Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category not found for this id = " + categoryId));
        return ResponseEntity.ok().body(category);
    }

    // SAVE category
    @PostMapping(path = "/categories", consumes = "application/json")
    public Category createCategory(@Valid @RequestBody Category category) {
        return this.categoryRepository.save(category);
    }

    // UPDATE category
    @PutMapping("/categories/{id}")
    public ResponseEntity<Category> updateCategory(@PathVariable(value = "id") Long categoryId, @Valid @RequestBody Category categoryDetails) throws ResourceNotFoundException {
        Category category = categoryRepository.findById(categoryId).orElseThrow( () ->
                new ResourceNotFoundException("Category not found for this id = " + categoryId));

        category.setName(categoryDetails.getName());
        category.setArticles(categoryDetails.getArticles());

        return ResponseEntity.ok(this.categoryRepository.save(category));
    }

    // DELETE category
    @DeleteMapping("/categories/{id}")
    public Map<String, Boolean> deleteCategory(@PathVariable (value = "id") Long categoryId) throws ResourceNotFoundException {
        Category category = categoryRepository.findById(categoryId).orElseThrow( () ->
                new ResourceNotFoundException("Category not found for this id = " + categoryId));

        this.categoryRepository.delete(category);

        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}
