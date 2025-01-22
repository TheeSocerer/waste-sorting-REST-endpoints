package com.enviro.assessment.grad001.tsheposhiburi.service;



import com.enviro.assessment.grad001.tsheposhiburi.payload.WasteCategoryDTO;

import java.util.List;

public interface WasteCategoryService {
    List<WasteCategoryDTO> getAllCategories();
    WasteCategoryDTO getCategoryById(Long id);
    WasteCategoryDTO addCategory(WasteCategoryDTO category);
    WasteCategoryDTO updateCategory(Long id, WasteCategoryDTO categorDTOy);
    void deleteCategory(Long id);
}

