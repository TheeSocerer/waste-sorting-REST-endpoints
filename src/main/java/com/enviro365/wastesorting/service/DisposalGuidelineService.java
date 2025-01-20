package com.enviro365.wastesorting.service;

import com.enviro365.wastesorting.model.DisposalGuideline;
import com.enviro365.wastesorting.payload.DisposalGuidelineDTO;

import java.util.List;

public interface DisposalGuidelineService {
    List<DisposalGuidelineDTO> getDisposalGuidelines();
    List<DisposalGuidelineDTO> getDisposalGuidelinesByCategoryId(Long categoryId);
    DisposalGuidelineDTO addDisposalGuideline(DisposalGuidelineDTO disposalGuidelineDTO);
}
