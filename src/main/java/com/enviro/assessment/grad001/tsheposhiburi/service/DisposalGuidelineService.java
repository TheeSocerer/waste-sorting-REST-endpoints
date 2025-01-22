package com.enviro.assessment.grad001.tsheposhiburi.service;


import com.enviro.assessment.grad001.tsheposhiburi.payload.DisposalGuidelineDTO;

import java.util.List;

public interface DisposalGuidelineService {
    List<DisposalGuidelineDTO> getDisposalGuidelines();
    List<DisposalGuidelineDTO> getDisposalGuidelinesByCategoryId(Long categoryId);
    DisposalGuidelineDTO addDisposalGuideline(DisposalGuidelineDTO disposalGuidelineDTO);
}
