package com.enviro.assessment.grad001.tsheposhiburi.controller;

import com.enviro.assessment.grad001.tsheposhiburi.payload.RecyclingTipDTO;
import com.enviro.assessment.grad001.tsheposhiburi.service.RecyclingTipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("api/tips")
public class RecyclingTipController {

    @Autowired
    private RecyclingTipService recyclingTipService;

    @GetMapping
    public ResponseEntity<List<RecyclingTipDTO>> getTips() {
        return ResponseEntity.ok(recyclingTipService.getAllRecyclingTips());
    }

    @GetMapping("/category/{id}")
    public ResponseEntity<List<RecyclingTipDTO>> getTipsByCategoryId(@PathVariable Long id) {
        return ResponseEntity.ok(recyclingTipService.getRecyclingTipsByCategory(id));
    }

    @GetMapping("{id}")
    public ResponseEntity<RecyclingTipDTO> getTipById(@PathVariable Long id) {
        return ResponseEntity.ok(recyclingTipService.getRecyclingTipById(id));
    }

    @PostMapping
    public ResponseEntity<RecyclingTipDTO> addTip(@RequestBody RecyclingTipDTO recyclingTipDTO) {
        return new ResponseEntity<>(recyclingTipService.addRecyclingTip(recyclingTipDTO),
                HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<RecyclingTipDTO> updateTip(@RequestBody RecyclingTipDTO recyclingTipDTO) {
        RecyclingTipDTO updatedTip = recyclingTipService.updateRecyclingTip(recyclingTipDTO);
        return new ResponseEntity<>(updatedTip, HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<String> deleteTipById(@RequestParam Long id) {
        recyclingTipService.deleteRecyclingTipById(id);
        return new ResponseEntity<>("Tip deleted", HttpStatus.OK);
    }
}
