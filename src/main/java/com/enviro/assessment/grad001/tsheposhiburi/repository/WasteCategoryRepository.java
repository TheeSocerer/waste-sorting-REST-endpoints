package com.enviro.assessment.grad001.tsheposhiburi.repository;


import com.enviro.assessment.grad001.tsheposhiburi.model.WasteCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WasteCategoryRepository extends JpaRepository<WasteCategory, Long> {
//    WasteCategory findWasteCategoryById(Long id);
}

