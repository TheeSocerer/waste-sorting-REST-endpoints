package com.enviro.assessment.grad001.tsheposhiburi.repository;

import com.enviro.assessment.grad001.tsheposhiburi.model.RecyclingTip;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RecyclingTipRepository extends JpaRepository<RecyclingTip, Long> {
    List<RecyclingTip> findRecyclingTipsByCategoryId(Long categoryId);
}
