package com.enviro365.wastesorting.controller;

import com.enviro365.wastesorting.payload.RecyclingTipDTO;
import com.enviro365.wastesorting.service.RecyclingTipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/tips")
public class RecyclingTipController {

    @Autowired
    private RecyclingTipService recyclingTipService;

    @GetMapping
    public ResponseEntity<List<RecyclingTipDTO>> getTips() {
        return ResponseEntity.ok(recyclingTipService.getAllRecyclingTips());
    }

    @GetMapping("{id}")
    public ResponseEntity<List<RecyclingTipDTO>> getTipsByCategoryId(@PathVariable Long id) {
        return ResponseEntity.ok(recyclingTipService.getRecyclingTipsByCategory(id));
    }
    @PostMapping
    public ResponseEntity<RecyclingTipDTO> addTip(@RequestBody RecyclingTipDTO recyclingTipDTO) {
        return new ResponseEntity<>(recyclingTipService.addRecyclingTip(recyclingTipDTO),
                HttpStatus.CREATED);
    }
}
