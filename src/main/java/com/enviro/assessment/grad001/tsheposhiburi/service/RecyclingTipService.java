package com.enviro.assessment.grad001.tsheposhiburi.service;



import com.enviro.assessment.grad001.tsheposhiburi.payload.RecyclingTipDTO;

import java.util.List;

public interface RecyclingTipService {
    List<RecyclingTipDTO> getAllRecyclingTips();
    List<RecyclingTipDTO> getRecyclingTipsByCategory(Long categoryId);
    RecyclingTipDTO addRecyclingTip(RecyclingTipDTO recyclingTipDTO);
    RecyclingTipDTO updateRecyclingTip(Long id,RecyclingTipDTO recyclingTipDTO);
    RecyclingTipDTO getRecyclingTipById(Long id);
    void deleteRecyclingTipById(Long id);
}
