package com.enviro.assessment.grad001.tsheposhiburi.service;


import com.enviro.assessment.grad001.tsheposhiburi.exception.ResourceNotFoundException;
import com.enviro.assessment.grad001.tsheposhiburi.model.WasteCategory;
import com.enviro.assessment.grad001.tsheposhiburi.payload.WasteCategoryDTO;
import com.enviro.assessment.grad001.tsheposhiburi.repository.WasteCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class WasteCategoryServiceImp implements WasteCategoryService {

    @Autowired
    private WasteCategoryRepository categoryRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<WasteCategoryDTO> getAllCategories() {
        List<WasteCategory> wasteCategories = categoryRepository.findAll();
        return wasteCategories.stream().map((category) -> modelMapper.map(category, WasteCategoryDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public WasteCategoryDTO getCategoryById(Long id) {

        WasteCategory category = categoryRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("WasteCategory","id", id ));
        return modelMapper.map(category, WasteCategoryDTO.class);
    }

    @Override
    public WasteCategoryDTO addCategory(WasteCategoryDTO categoryDTO) {

        WasteCategory category = modelMapper.map(categoryDTO, WasteCategory.class);
        category = categoryRepository.save(category);
        return modelMapper.map(category, WasteCategoryDTO.class);
    }

    @Override
    public WasteCategoryDTO updateCategory(Long id, WasteCategoryDTO categoryDTO) {
        WasteCategory category = categoryRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("WasteCategory","id", id ));

        category.setName(categoryDTO.getName());
        category.setDescription(categoryDTO.getDescription());
        category = categoryRepository.save(category);
        return modelMapper.map(category, WasteCategoryDTO.class);


    }

    @Override
    public void deleteCategory(Long id) {

        WasteCategory category = categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Waste category", "id", id));

        categoryRepository.delete(category);
    }
}
