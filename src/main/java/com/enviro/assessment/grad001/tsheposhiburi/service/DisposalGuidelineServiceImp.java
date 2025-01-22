package com.enviro.assessment.grad001.tsheposhiburi.service;


import com.enviro.assessment.grad001.tsheposhiburi.exception.Enviro365ExceptionHandler;
import com.enviro.assessment.grad001.tsheposhiburi.exception.ResourceNotFoundException;
import com.enviro.assessment.grad001.tsheposhiburi.model.DisposalGuideline;
import com.enviro.assessment.grad001.tsheposhiburi.model.WasteCategory;
import com.enviro.assessment.grad001.tsheposhiburi.payload.DisposalGuidelineDTO;
import com.enviro.assessment.grad001.tsheposhiburi.repository.DisposalGuidelineRepository;
import com.enviro.assessment.grad001.tsheposhiburi.repository.WasteCategoryRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DisposalGuidelineServiceImp implements DisposalGuidelineService {

    @Autowired
    private DisposalGuidelineRepository disposalGuidelineRepository;
    @Autowired
    private WasteCategoryRepository wasteCategoryRepository;
    @Autowired
    private ModelMapper mapper;

    @Override
    public List<DisposalGuidelineDTO> getDisposalGuidelines() {
        List<DisposalGuideline> disposalGuideline = disposalGuidelineRepository.findAll();

        return disposalGuideline.stream().map((guideline) -> mapper.map(guideline, DisposalGuidelineDTO.class))
                .collect(Collectors.toList());
    }


    @Override
    public List<DisposalGuidelineDTO> getDisposalGuidelinesByCategoryId(Long categoryId) {
        List<DisposalGuideline> disposalGuideline = disposalGuidelineRepository.findDisposalGuidelinesByCategoryId(categoryId);

        // convert list of disposalguideline entities to list of disposalguideline dto's
        return disposalGuideline.stream().map((guideline) -> mapper.map(guideline, DisposalGuidelineDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public DisposalGuidelineDTO addDisposalGuideline(DisposalGuidelineDTO disposalGuidelineDTO) {
        DisposalGuideline guideline = mapper.map(disposalGuidelineDTO, DisposalGuideline.class);

        WasteCategory wasteCategory = wasteCategoryRepository.findById(disposalGuidelineDTO.getCategoryId())
                .orElseThrow(()-> new ResourceNotFoundException("Waste Category","id", disposalGuidelineDTO.getCategoryId()));
        DisposalGuideline savedGuideline = disposalGuidelineRepository.save(guideline);

        return mapper.map(savedGuideline, DisposalGuidelineDTO.class);
    }

    @Override
    public DisposalGuidelineDTO getDisposalGuidelineById(Long id) {
        DisposalGuideline guideline = disposalGuidelineRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Disposal Guideline","id",id));

        return mapper.map(guideline, DisposalGuidelineDTO.class);
    }

    @Override
    public DisposalGuidelineDTO updateDisposalGuideline(DisposalGuidelineDTO disposalGuidelineDTO) {
        DisposalGuideline guideline = disposalGuidelineRepository.findById(disposalGuidelineDTO.getId())
                .orElseThrow(()-> new ResourceNotFoundException("Disposal Guideline","id",disposalGuidelineDTO.getId()));

        WasteCategory wasteCategory = wasteCategoryRepository.findById(disposalGuidelineDTO.getCategoryId())
                .orElseThrow(()-> new ResourceNotFoundException("Waste Category","id", disposalGuidelineDTO.getCategoryId()));

        if(!guideline.getCategory().getId().equals(wasteCategory.getId())) {
            throw new Enviro365ExceptionHandler(HttpStatus.BAD_REQUEST, "Disposal Guideline does not belong to Waste Category");
        }

        guideline.setCategory(wasteCategory);
        guideline.setGuideline(disposalGuidelineDTO.getGuideline());
        return mapper.map(disposalGuidelineRepository.save(guideline), DisposalGuidelineDTO.class);
    }
    @Override
    public void deleteDisposalGuidelineById(Long id) {
        DisposalGuideline guideline = disposalGuidelineRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Disposal Guideline","id",id));
        disposalGuidelineRepository.delete(guideline);
    }
}
