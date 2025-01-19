package com.enviro365.wastesorting.repository;


import com.enviro365.wastesorting.model.WasteCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WasteCategoryRepository extends JpaRepository<WasteCategory, Long> {
//    WasteCategory findWasteCategoryById(Long id);
}

