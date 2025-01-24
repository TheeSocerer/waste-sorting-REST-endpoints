package com.enviro.assessment.grad001.tsheposhiburi.service;


import com.enviro.assessment.grad001.tsheposhiburi.exception.Enviro365ExceptionHandler;
import com.enviro.assessment.grad001.tsheposhiburi.exception.ResourceNotFoundException;
import com.enviro.assessment.grad001.tsheposhiburi.model.RecyclingTip;
import com.enviro.assessment.grad001.tsheposhiburi.model.WasteCategory;
import com.enviro.assessment.grad001.tsheposhiburi.payload.RecyclingTipDTO;
import com.enviro.assessment.grad001.tsheposhiburi.repository.RecyclingTipRepository;
import com.enviro.assessment.grad001.tsheposhiburi.repository.WasteCategoryRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RecyclingTipServiceImp implements RecyclingTipService {

    @Autowired
    private RecyclingTipRepository recyclingTipRepository;

    @Autowired
    private WasteCategoryRepository wasteCategoryRepository;

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

    @Override
    public RecyclingTipDTO updateRecyclingTip(RecyclingTipDTO recyclingTipDTO) {
        RecyclingTip tip = recyclingTipRepository.findById(recyclingTipDTO.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Recycling Tip", "id", recyclingTipDTO.getId()));

        WasteCategory wasteCategory = wasteCategoryRepository.findById(recyclingTipDTO.getCategoryId())
                .orElseThrow(() -> new ResourceNotFoundException("Waste Category", "id", recyclingTipDTO.getCategoryId()));

        // Check if the RecyclingTip's category is null or if the IDs do not match
        if (tip.getCategory() == null || !tip.getCategory().getId().equals(wasteCategory.getId())) {
            throw new Enviro365ExceptionHandler(HttpStatus.BAD_REQUEST, "Disposal Guideline does not belong to Waste Category");
        }

        tip.setCategory(wasteCategory);
        tip.setTip(recyclingTipDTO.getTip());
        tip.setId(recyclingTipDTO.getId());
        return modelMapper.map(recyclingTipRepository.save(tip), RecyclingTipDTO.class);
    }


    @Override
    public RecyclingTipDTO getRecyclingTipById(Long id) {
        RecyclingTip tip = recyclingTipRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Recycling Tip","id",id));
        return modelMapper.map(tip, RecyclingTipDTO.class);
    }

    @Override
    public void deleteRecyclingTipById(Long id) {
        RecyclingTip tip = recyclingTipRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Recycling Tip","id",id));
        recyclingTipRepository.delete(tip);
    }


}
