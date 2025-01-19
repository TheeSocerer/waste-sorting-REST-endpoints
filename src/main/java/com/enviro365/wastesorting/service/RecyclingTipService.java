package com.enviro365.wastesorting.service;

import com.enviro365.wastesorting.payload.RecyclingTipDTO;

import java.util.List;

public interface RecyclingTipService {
    List<RecyclingTipDTO> getAllRecyclingTips();
    List<RecyclingTipDTO> getRecyclingTipsByCategory(Long categoryId);
    RecyclingTipDTO addRecyclingTip(RecyclingTipDTO recyclingTipDTO);

}
