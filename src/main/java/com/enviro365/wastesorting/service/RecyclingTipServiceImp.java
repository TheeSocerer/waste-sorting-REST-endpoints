package com.enviro365.wastesorting.service;

import com.enviro365.wastesorting.model.RecyclingTip;
import com.enviro365.wastesorting.payload.RecyclingTipDTO;
import com.enviro365.wastesorting.repository.RecyclingTipRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RecyclingTipServiceImp implements RecyclingTipService {

    @Autowired
    private RecyclingTipRepository recyclingTipRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<RecyclingTipDTO> getAllRecyclingTips() {
        List<RecyclingTip> recyclingTips = recyclingTipRepository.findAll();
        return recyclingTips.stream().map((recyclingTip) -> modelMapper.map(recyclingTip, RecyclingTipDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<RecyclingTipDTO> getRecyclingTipsByCategory(Long categoryId) {
        List<RecyclingTip> recyclingTips = recyclingTipRepository.findRecyclingTipsByCategoryId(categoryId);
        return recyclingTips.stream().map((recyclingTip) -> modelMapper.map(recyclingTip, RecyclingTipDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public RecyclingTipDTO addRecyclingTip(RecyclingTipDTO recyclingTipDTO) {
        RecyclingTip recyclingTip = modelMapper.map(recyclingTipDTO, RecyclingTip.class);
        RecyclingTip savedRecyclingTip = recyclingTipRepository.save(recyclingTip);

        return modelMapper.map(savedRecyclingTip, RecyclingTipDTO.class);
    }
}