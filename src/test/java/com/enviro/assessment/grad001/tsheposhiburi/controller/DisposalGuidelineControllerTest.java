package com.enviro.assessment.grad001.tsheposhiburi.controller;

import com.enviro.assessment.grad001.tsheposhiburi.payload.DisposalGuidelineDTO;
import com.enviro.assessment.grad001.tsheposhiburi.service.DisposalGuidelineService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(DisposalGuidelineController.class)
public class DisposalGuidelineControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @InjectMocks
    private DisposalGuidelineService disposalGuidelineService;

    @Autowired
    private ObjectMapper objectMapper;

    private DisposalGuidelineDTO guidelineDTO;

    @BeforeEach
    void setUp() {
        guidelineDTO = new DisposalGuidelineDTO();
        guidelineDTO.setId(1L);
        guidelineDTO.setCategoryId(2L);
        guidelineDTO.setGuideline("Dispose of electronic waste at certified e-waste centers.");
    }

    @Test
    void testGetAllDisposalGuidelines_Success() throws Exception {
        List<DisposalGuidelineDTO> guidelines = Arrays.asList(guidelineDTO);
        Mockito.when(disposalGuidelineService.getDisposalGuidelines()).thenReturn(guidelines);

        mockMvc.perform(get("/api/guidelines")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].guideline", is("Dispose of electronic waste at certified e-waste centers.")));
    }

    @Test
    void testGetDisposalGuidelineByCategoryId_Success() throws Exception {
        List<DisposalGuidelineDTO> guidelines = Collections.singletonList(guidelineDTO);
        Mockito.when(disposalGuidelineService.getDisposalGuidelinesByCategoryId(anyLong())).thenReturn(guidelines);

        mockMvc.perform(get("/api/guidelines/category/2")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].categoryId", is(2)))
                .andExpect(jsonPath("$[0].guideline", is("Dispose of electronic waste at certified e-waste centers.")));
    }

    @Test
    void testGetDisposalGuidelineById_Success() throws Exception {
        Mockito.when(disposalGuidelineService.getDisposalGuidelineById(anyLong())).thenReturn(guidelineDTO);

        mockMvc.perform(get("/api/guidelines/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.guideline", is("Dispose of electronic waste at certified e-waste centers.")));
    }

    @Test
    void testAddDisposalGuideline_Success() throws Exception {
        Mockito.when(disposalGuidelineService.addDisposalGuideline(any(DisposalGuidelineDTO.class))).thenReturn(guidelineDTO);

        mockMvc.perform(post("/api/guidelines")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(guidelineDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.guideline", is("Dispose of electronic waste at certified e-waste centers.")));
    }

    @Test
    void testUpdateDisposalGuideline_Success() throws Exception {
        guidelineDTO.setGuideline("Updated disposal guideline for electronic waste.");
        Mockito.when(disposalGuidelineService.updateDisposalGuideline(anyLong(),any(DisposalGuidelineDTO.class))).thenReturn(guidelineDTO);

        mockMvc.perform(put("/api/guidelines")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(guidelineDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.guideline", is("Updated disposal guideline for electronic waste.")));
    }

    @Test
    void testDeleteDisposalGuidelineById_Success() throws Exception {
        Mockito.doNothing().when(disposalGuidelineService).deleteDisposalGuidelineById(anyLong());

        mockMvc.perform(delete("/api/guidelines")
                        .param("id", "1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("Disposal Guideline deleted"));
    }

    @Test
    void testGetDisposalGuidelineById_NotFound() throws Exception {
        Mockito.when(disposalGuidelineService.getDisposalGuidelineById(anyLong()))
                .thenThrow(new RuntimeException("Disposal Guideline not found with id: 1"));

        mockMvc.perform(get("/api/guidelines/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isInternalServerError())
                .andExpect(jsonPath("$.message", is("Disposal Guideline not found with id: 1")));
    }
}
