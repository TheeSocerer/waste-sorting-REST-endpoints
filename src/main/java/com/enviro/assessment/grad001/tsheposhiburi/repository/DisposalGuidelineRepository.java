package com.enviro.assessment.grad001.tsheposhiburi.repository;

import com.enviro.assessment.grad001.tsheposhiburi.model.DisposalGuideline;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface DisposalGuidelineRepository extends JpaRepository<DisposalGuideline, Long> {
    List<DisposalGuideline> findDisposalGuidelinesByCategoryId(Long categoryId);
}
