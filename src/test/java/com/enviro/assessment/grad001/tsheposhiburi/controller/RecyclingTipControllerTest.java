package com.enviro.assessment.grad001.tsheposhiburi.controller;

import com.enviro.assessment.grad001.tsheposhiburi.payload.RecyclingTipDTO;
import com.enviro.assessment.grad001.tsheposhiburi.service.RecyclingTipService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
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

@WebMvcTest(RecyclingTipController.class)
public class RecyclingTipControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RecyclingTipService recyclingTipService;

    @Autowired
    private ObjectMapper objectMapper;

    private RecyclingTipDTO tipDTO;

    @BeforeEach
    void setUp() {
        tipDTO = new RecyclingTipDTO();
        tipDTO.setId(1L);
        tipDTO.setCategoryId(2L);
        tipDTO.setTip("Recycle paper to save trees.");
    }

    @Test
    void testGetTips_Success() throws Exception {
        List<RecyclingTipDTO> tips = Arrays.asList(tipDTO);
        Mockito.when(recyclingTipService.getAllRecyclingTips()).thenReturn(tips);

        mockMvc.perform(get("/api/tips")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].tip", is("Recycle paper to save trees.")));
    }

    @Test
    void testGetTipsByCategoryId_Success() throws Exception {
        List<RecyclingTipDTO> tips = Collections.singletonList(tipDTO);
        Mockito.when(recyclingTipService.getRecyclingTipsByCategory(anyLong())).thenReturn(tips);

        mockMvc.perform(get("/api/tips/category/2")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].categoryId", is(2)))
                .andExpect(jsonPath("$[0].tip", is("Recycle paper to save trees.")));
    }

    @Test
    void testGetTipById_Success() throws Exception {
        Mockito.when(recyclingTipService.getRecyclingTipById(anyLong())).thenReturn(tipDTO);

        mockMvc.perform(get("/api/tips/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.tip", is("Recycle paper to save trees.")));
    }

    @Test
    void testAddTip_Success() throws Exception {
        Mockito.when(recyclingTipService.addRecyclingTip(any(RecyclingTipDTO.class))).thenReturn(tipDTO);

        mockMvc.perform(post("/api/tips")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(tipDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.tip", is("Recycle paper to save trees.")));
    }

    @Test
    void testUpdateTip_Success() throws Exception {
        tipDTO.setTip("Updated recycling tip.");
        Mockito.when(recyclingTipService.updateRecyclingTip(any(RecyclingTipDTO.class))).thenReturn(tipDTO);

        mockMvc.perform(put("/api/tips")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(tipDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.tip", is("Updated recycling tip.")));
    }

    @Test
    void testDeleteTipById_Success() throws Exception {
        Mockito.doNothing().when(recyclingTipService).deleteRecyclingTipById(anyLong());

        mockMvc.perform(delete("/api/tips")
                        .param("id", "1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("Tip deleted"));
    }

    @Test
    void testGetTipById_NotFound() throws Exception {
        Mockito.when(recyclingTipService.getRecyclingTipById(anyLong()))
                .thenThrow(new RuntimeException("Tip not found with id: 1"));

        mockMvc.perform(get("/api/tips/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isInternalServerError())
                .andExpect(jsonPath("$.message", is("Tip not found with id: 1")));
    }
}
