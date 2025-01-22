package com.enviro.assessment.grad001.tsheposhiburi.service;


import com.enviro.assessment.grad001.tsheposhiburi.model.DisposalGuideline;
import com.enviro.assessment.grad001.tsheposhiburi.payload.DisposalGuidelineDTO;
import com.enviro.assessment.grad001.tsheposhiburi.repository.DisposalGuidelineRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DisposalGuidelineServiceImp implements DisposalGuidelineService {

    @Autowired
    private DisposalGuidelineRepository disposalGuidelineRepository;
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
        DisposalGuideline savedGuideline = disposalGuidelineRepository.save(guideline);

        return mapper.map(savedGuideline, DisposalGuidelineDTO.class);
    }
}
