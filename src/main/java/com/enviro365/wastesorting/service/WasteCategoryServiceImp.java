package com.enviro365.wastesorting.service;

import com.enviro365.wastesorting.exception.ResourceNotFoundException;
import com.enviro365.wastesorting.model.WasteCategory;
import com.enviro365.wastesorting.repository.WasteCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.MissingRequestValueException;

import java.util.List;
import java.util.MissingResourceException;

@Service
public class WasteCategoryServiceImp implements WasteCategoryService {

    @Autowired
    private WasteCategoryRepository repository;

    @Override
    public List<WasteCategory> getAllCategories() {
        return repository.findAll();
    }

    @Override
    public WasteCategory getCategoryById(Long id) {
        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("WasteCategory","id", id ));
    }

    @Override
    public WasteCategory addCategory(WasteCategory category) {
        return repository.save(category);
    }

    @Override
    public WasteCategory updateCategory(Long id, WasteCategory category) {
        WasteCategory existing = getCategoryById(id);

        existing.setName(category.getName());
        existing.setDescription(category.getDescription());
        return repository.save(existing);
    }

    @Override
    public void deleteCategory(Long id) {

        repository.deleteById(id);
    }
}
