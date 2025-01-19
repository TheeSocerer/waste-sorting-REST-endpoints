package com.enviro365.wastesorting.controller;

import com.enviro365.wastesorting.model.WasteCategory;
import com.enviro365.wastesorting.service.WasteCategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class WasteCategoryController {
    @Autowired
    private WasteCategoryService categoryService;

    @GetMapping
    public ResponseEntity<List<WasteCategory>> getAllCategories() {
        return ResponseEntity.ok(categoryService.getAllCategories());
    }

    @GetMapping("/{id}")
    public ResponseEntity<WasteCategory> getCategoryById(@PathVariable Long id) {
        return ResponseEntity.ok(categoryService.getCategoryById(id));
    }

    @PostMapping
    public ResponseEntity<WasteCategory> addCategory(@Valid @RequestBody WasteCategory category) {
        return new ResponseEntity<>(category, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<WasteCategory> updateCategory(@PathVariable Long id, @Valid @RequestBody WasteCategory category) {
        return ResponseEntity.ok(categoryService.updateCategory(id, category));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCategory(@PathVariable Long id) {
        return ResponseEntity.ok("Category deleted successfully!.");
    }
}

