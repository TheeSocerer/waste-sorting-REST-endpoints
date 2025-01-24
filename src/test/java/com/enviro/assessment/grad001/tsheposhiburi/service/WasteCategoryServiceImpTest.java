package com.enviro.assessment.grad001.tsheposhiburi.service;

import com.enviro.assessment.grad001.tsheposhiburi.exception.ResourceNotFoundException;
import com.enviro.assessment.grad001.tsheposhiburi.model.WasteCategory;
import com.enviro.assessment.grad001.tsheposhiburi.payload.WasteCategoryDTO;
import com.enviro.assessment.grad001.tsheposhiburi.repository.WasteCategoryRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class WasteCategoryServiceImpTest {

    @Mock
    private WasteCategoryRepository wasteCategoryRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private WasteCategoryServiceImp wasteCategoryService;

    private WasteCategory wasteCategory;
    private WasteCategoryDTO wasteCategoryDTO;

    @BeforeEach
    void setUp() {
        wasteCategory = new WasteCategory();
        wasteCategory.setId(1L);
        wasteCategory.setName("Plastic");
        wasteCategory.setDescription("Plastic waste");

        wasteCategoryDTO = new WasteCategoryDTO();
        wasteCategoryDTO.setName("Plastic");
        wasteCategoryDTO.setDescription("Plastic waste");
    }

    @Test
    void testGetAllCategories() {
        // Mocking repository call
        when(wasteCategoryRepository.findAll()).thenReturn(Arrays.asList(wasteCategory));
        when(modelMapper.map(any(WasteCategory.class), eq(WasteCategoryDTO.class))).thenReturn(wasteCategoryDTO);

        // Testing the service method
        var result = wasteCategoryService.getAllCategories();

        // Verifying the result
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Plastic", result.get(0).getName());
        verify(wasteCategoryRepository, times(1)).findAll();
    }

    @Test
    void testGetCategoryById_Success() {
        // Mocking repository call
        when(wasteCategoryRepository.findById(anyLong())).thenReturn(Optional.of(wasteCategory));
        when(modelMapper.map(any(WasteCategory.class), eq(WasteCategoryDTO.class))).thenReturn(wasteCategoryDTO);

        // Testing the service method
        WasteCategoryDTO result = wasteCategoryService.getCategoryById(1L);

        // Verifying the result
        assertNotNull(result);
        assertEquals("Plastic", result.getName());
        verify(wasteCategoryRepository, times(1)).findById(1L);
    }

    @Test
    void testGetCategoryById_NotFound() {
        // Mocking repository call
        when(wasteCategoryRepository.findById(anyLong())).thenReturn(Optional.empty());

        // Testing the service method
        ResourceNotFoundException thrown = assertThrows(ResourceNotFoundException.class, () -> {
            wasteCategoryService.getCategoryById(1L);
        });

        // Verifying the exception message
        assertEquals("WasteCategory not found with id : '1'", thrown.getMessage());
    }

    @Test
    void testAddCategory_Success() {
        // Mocking repository call
        when(wasteCategoryRepository.save(any(WasteCategory.class))).thenReturn(wasteCategory);
        when(modelMapper.map(any(WasteCategory.class), eq(WasteCategoryDTO.class))).thenReturn(wasteCategoryDTO);

        // Testing the service method
        WasteCategoryDTO result = wasteCategoryService.addCategory(wasteCategoryDTO);

        // Verifying the result
        assertNotNull(result);
        assertEquals("Plastic", result.getName());
        assertEquals("Plastic waste", result.getDescription());
        verify(wasteCategoryRepository, times(1)).save(any(WasteCategory.class));
    }

    @Test
    void testUpdateCategory_Success() {
        // Mocking repository call
        when(wasteCategoryRepository.findById(anyLong())).thenReturn(Optional.of(wasteCategory));
        when(wasteCategoryRepository.save(any(WasteCategory.class))).thenReturn(wasteCategory);
        when(modelMapper.map(any(WasteCategory.class), eq(WasteCategoryDTO.class))).thenReturn(wasteCategoryDTO);

        // Testing the service method
        WasteCategoryDTO result = wasteCategoryService.updateCategory(1L, wasteCategoryDTO);

        // Verifying the result
        assertNotNull(result);
        assertEquals("Plastic", result.getName());
        assertEquals("Plastic waste", result.getDescription());
        verify(wasteCategoryRepository, times(1)).save(any(WasteCategory.class));
    }

    @Test
    void testUpdateCategory_NotFound() {
        // Mocking repository call
        when(wasteCategoryRepository.findById(anyLong())).thenReturn(Optional.empty());

        // Testing the service method
        ResourceNotFoundException thrown = assertThrows(ResourceNotFoundException.class, () -> {
            wasteCategoryService.updateCategory(1L, wasteCategoryDTO);
        });

        // Verifying the exception message
        assertEquals("WasteCategory not found with id : '1'", thrown.getMessage());
    }

    @Test
    void testDeleteCategory_Success() {
        // Mocking repository call
        when(wasteCategoryRepository.findById(anyLong())).thenReturn(Optional.of(wasteCategory));
        doNothing().when(wasteCategoryRepository).delete(any(WasteCategory.class));

        // Testing the service method
        wasteCategoryService.deleteCategory(1L);

        // Verifying the repository interaction
        verify(wasteCategoryRepository, times(1)).delete(any(WasteCategory.class));
    }

    @Test
    void testDeleteCategory_NotFound() {
        // Mocking repository call
        when(wasteCategoryRepository.findById(anyLong())).thenReturn(Optional.empty());

        // Testing the service method
        ResourceNotFoundException thrown = assertThrows(ResourceNotFoundException.class, () -> {
            wasteCategoryService.deleteCategory(1L);
        });

        // Verifying the exception message
        assertEquals("Waste category not found with id : '1'", thrown.getMessage());
    }
}
