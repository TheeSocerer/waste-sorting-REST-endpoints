package com.enviro365.wastesorting.repository;

import com.enviro365.wastesorting.model.DisposalGuideline;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface DisposalGuidelineRepository extends JpaRepository<DisposalGuideline, Long> {
//    List<DisposalGuideline> findDisposalGuidelinesByCategoryId(Long categoryId);
}
