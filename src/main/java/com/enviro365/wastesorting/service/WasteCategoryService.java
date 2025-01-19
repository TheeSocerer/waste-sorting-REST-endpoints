package com.enviro365.wastesorting.service;

import com.enviro365.wastesorting.model.WasteCategory;
import java.util.List;

public interface WasteCategoryService {
    List<WasteCategory> getAllCategories();
    WasteCategory getCategoryById(Long id);
    WasteCategory saveCategory(WasteCategory category);
    WasteCategory updateCategory(Long id, WasteCategory category);
    void deleteCategory(Long id);
}

