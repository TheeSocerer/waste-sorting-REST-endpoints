package com.enviro365.wastesorting.service;

import com.enviro365.wastesorting.model.WasteCategory;
import com.enviro365.wastesorting.payload.WasteCategoryDTO;

import java.util.List;

public interface WasteCategoryService {
    List<WasteCategoryDTO> getAllCategories();
    WasteCategoryDTO getCategoryById(Long id);
    WasteCategoryDTO addCategory(WasteCategory category);
    WasteCategoryDTO updateCategory(Long id, WasteCategory category);
    void deleteCategory(Long id);
}

