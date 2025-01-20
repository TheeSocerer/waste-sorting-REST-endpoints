package com.enviro365.wastesorting.controller;

import com.enviro365.wastesorting.payload.DisposalGuidelineDTO;
import com.enviro365.wastesorting.service.DisposalGuidelineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/disposalguidelines")
public class DisposalGuidelineController {

    @Autowired
    private DisposalGuidelineService disposalGuidelineService;

    @GetMapping
    public ResponseEntity<List<DisposalGuidelineDTO>> getAllDisposalGuidelines() {
        return ResponseEntity.ok(disposalGuidelineService.getDisposalGuidelines());
    }

    @GetMapping("{id}")
    public ResponseEntity<List<DisposalGuidelineDTO>> getDisposalGuidelineByCategoryId(@PathVariable Long id) {
        List<DisposalGuidelineDTO> disposalGuidelineDTOS = disposalGuidelineService.getDisposalGuidelinesByCategoryId(id);
        return ResponseEntity.ok(disposalGuidelineDTOS);
    }

    @PostMapping
    public ResponseEntity<DisposalGuidelineDTO> addDisposalGuideline(@RequestBody DisposalGuidelineDTO disposalGuidelineDTO) {
        DisposalGuidelineDTO response = disposalGuidelineService.addDisposalGuideline(disposalGuidelineDTO);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
