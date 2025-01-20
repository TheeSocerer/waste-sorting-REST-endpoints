package com.enviro365.wastesorting.repository;

import com.enviro365.wastesorting.model.RecyclingTip;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RecyclingTipRepository extends JpaRepository<RecyclingTip, Long> {
    List<RecyclingTip> findRecyclingTipsByCategoryId(Long categoryId);
}
