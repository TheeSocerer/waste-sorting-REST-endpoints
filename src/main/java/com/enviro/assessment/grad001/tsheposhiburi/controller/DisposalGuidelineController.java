package com.enviro.assessment.grad001.tsheposhiburi.controller;

import com.enviro.assessment.grad001.tsheposhiburi.payload.DisposalGuidelineDTO;
import com.enviro.assessment.grad001.tsheposhiburi.service.DisposalGuidelineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/guidelines")
public class DisposalGuidelineController {

    @Autowired
    private DisposalGuidelineService disposalGuidelineService;

    @GetMapping
    public ResponseEntity<List<DisposalGuidelineDTO>> getAllDisposalGuidelines() {
        return ResponseEntity.ok(disposalGuidelineService.getDisposalGuidelines());
    }

    @GetMapping("/category/{id}")
    public ResponseEntity<List<DisposalGuidelineDTO>> getDisposalGuidelineByCategoryId(@PathVariable Long id) {
        List<DisposalGuidelineDTO> disposalGuidelineDTOS = disposalGuidelineService.getDisposalGuidelinesByCategoryId(id);
        return ResponseEntity.ok(disposalGuidelineDTOS);
    }

    @GetMapping("{id}")
    public ResponseEntity<DisposalGuidelineDTO> getDisposalGuidelineById(@PathVariable Long id) {
        return ResponseEntity.ok(disposalGuidelineService.getDisposalGuidelineById(id));
    }

    @PostMapping
    public ResponseEntity<DisposalGuidelineDTO> addDisposalGuideline(@RequestBody DisposalGuidelineDTO disposalGuidelineDTO) {
        DisposalGuidelineDTO response = disposalGuidelineService.addDisposalGuideline(disposalGuidelineDTO);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<DisposalGuidelineDTO> updateDisposalGuideline(@RequestBody DisposalGuidelineDTO disposalGuidelineDTO) {
        DisposalGuidelineDTO updatedGuideline = disposalGuidelineService.updateDisposalGuideline(disposalGuidelineDTO);
        return new ResponseEntity<>(updatedGuideline, HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<String> deleteDisposalGuidelineById(@RequestParam Long id) {
        disposalGuidelineService.deleteDisposalGuidelineById(id);
        return new ResponseEntity<>("Disposal Guideline deleted", HttpStatus.OK);
    }
}
