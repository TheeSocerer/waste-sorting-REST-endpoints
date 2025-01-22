package com.enviro.assessment.grad001.tsheposhiburi.controller;

import com.enviro.assessment.grad001.tsheposhiburi.payload.WasteCategoryDTO;
import com.enviro.assessment.grad001.tsheposhiburi.service.WasteCategoryService;
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
    public ResponseEntity<List<WasteCategoryDTO>> getAllCategories() {
        return ResponseEntity.ok(categoryService.getAllCategories());
    }

    @GetMapping("/{id}")
    public ResponseEntity<WasteCategoryDTO> getCategoryById(@PathVariable Long id) {
        return ResponseEntity.ok(categoryService.getCategoryById(id));
    }

    @PostMapping
    public ResponseEntity<WasteCategoryDTO> addCategory(@Valid @RequestBody WasteCategoryDTO categoryDTO) {
        return new ResponseEntity<>(categoryDTO, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<WasteCategoryDTO> updateCategory(@PathVariable Long id, @Valid @RequestBody WasteCategoryDTO categoryDTO) {
        return ResponseEntity.ok(categoryService.updateCategory(id, categoryDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCategory(@PathVariable Long id) {
        return ResponseEntity.ok("Category deleted successfully!.");
    }
}

