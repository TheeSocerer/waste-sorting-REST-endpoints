package com.enviro.assessment.grad001.tsheposhiburi.controller;

import com.enviro.assessment.grad001.tsheposhiburi.exception.ResourceNotFoundException;
import com.enviro.assessment.grad001.tsheposhiburi.payload.WasteCategoryDTO;
import com.enviro.assessment.grad001.tsheposhiburi.service.WasteCategoryService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(MockitoExtension.class)
public class WasteCategoryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Mock
    private WasteCategoryService wasteCategoryService;

    @InjectMocks
    private WasteCategoryController controller;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    public void testGetAllCategories() throws Exception {
        WasteCategoryDTO category1 = new WasteCategoryDTO(1L, "Organic", "Biodegradable waste category");
        WasteCategoryDTO category2 = new WasteCategoryDTO(2L, "Recyclable", "Materials that can be recycled");
        List<WasteCategoryDTO> categories = Arrays.asList(category1, category2);

        when(wasteCategoryService.getAllCategories()).thenReturn(categories);

        mockMvc.perform(get("/api/categories")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(2))
                .andExpect(jsonPath("$[0].name").value("Organic"))
                .andExpect(jsonPath("$[1].name").value("Recyclable"));

        verify(wasteCategoryService, times(1)).getAllCategories();
    }

    @Test
    public void testGetCategoryById() throws Exception {
        WasteCategoryDTO category = new WasteCategoryDTO(1L, "Organic", "Biodegradable waste category");

        when(wasteCategoryService.getCategoryById(1L)).thenReturn(category);

        mockMvc.perform(get("/api/categories/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Organic"))
                .andExpect(jsonPath("$.description").value("Biodegradable waste category"));

        verify(wasteCategoryService, times(1)).getCategoryById(1L);
    }

    @Test
    public void testGetCategoryById_NotFound() throws Exception {
        when(wasteCategoryService.getCategoryById(1L))
                .thenThrow(new ResourceNotFoundException("WasteCategory", "id", 1L));

        mockMvc.perform(get("/api/categories/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());

        verify(wasteCategoryService, times(1)).getCategoryById(1L);
    }


    @Test
    public void testAddCategory() throws Exception {
        WasteCategoryDTO category = new WasteCategoryDTO(1L, "Organic", "Biodegradable waste category");

        when(wasteCategoryService.addCategory(any(WasteCategoryDTO.class))).thenReturn(category);

        mockMvc.perform(post("/api/categories")
                        .content(objectMapper.writeValueAsString(category))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Organic"))
                .andExpect(jsonPath("$.description").value("Biodegradable waste category"));

        verify(wasteCategoryService, times(1)).addCategory(any(WasteCategoryDTO.class));
    }

    @Test
    public void testUpdateCategory() throws Exception {
        WasteCategoryDTO updatedCategory = new WasteCategoryDTO(1L, "Organic", "Updated description");

        when(wasteCategoryService.updateCategory(eq(1L), any(WasteCategoryDTO.class))).thenReturn(updatedCategory);

        mockMvc.perform(put("/api/categories/1")
                        .content(objectMapper.writeValueAsString(updatedCategory))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.description").value("Updated description"));

        verify(wasteCategoryService, times(1)).updateCategory(eq(1L), any(WasteCategoryDTO.class));
    }

    @Test
    public void testUpdateCategory_NotFound() throws Exception {
        WasteCategoryDTO updatedCategory = new WasteCategoryDTO(1L, "Organic", "Updated description");

        when(wasteCategoryService.updateCategory(eq(1L), any(WasteCategoryDTO.class)))
                .thenThrow(new ResourceNotFoundException("WasteCategory", "id", 1L));

        mockMvc.perform(put("/api/categories/1")
                        .content(objectMapper.writeValueAsString(updatedCategory))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());

        verify(wasteCategoryService, times(1)).updateCategory(eq(1L), any(WasteCategoryDTO.class));
    }

    @Test
    public void testDeleteCategory() throws Exception {
        doNothing().when(wasteCategoryService).deleteCategory(1L);

        mockMvc.perform(delete("/api/categories/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("Category deleted successfully!."));

        verify(wasteCategoryService, times(1)).deleteCategory(1L);
    }

    @Test
    public void testDeleteCategory_NotFound() throws Exception {
        doThrow(new ResourceNotFoundException("Waste category", "id", 1L))
                .when(wasteCategoryService).deleteCategory(1L);

        mockMvc.perform(delete("/api/categories/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
        verify(wasteCategoryService, times(1)).deleteCategory(1L);
    }
}