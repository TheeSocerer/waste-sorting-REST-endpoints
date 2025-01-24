package com.enviro.assessment.grad001.tsheposhiburi.service;

import com.enviro.assessment.grad001.tsheposhiburi.exception.Enviro365ExceptionHandler;
import com.enviro.assessment.grad001.tsheposhiburi.exception.ResourceNotFoundException;
import com.enviro.assessment.grad001.tsheposhiburi.model.RecyclingTip;
import com.enviro.assessment.grad001.tsheposhiburi.model.WasteCategory;
import com.enviro.assessment.grad001.tsheposhiburi.payload.RecyclingTipDTO;
import com.enviro.assessment.grad001.tsheposhiburi.repository.RecyclingTipRepository;
import com.enviro.assessment.grad001.tsheposhiburi.repository.WasteCategoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RecyclingTipServiceImpTest {

    @Mock
    private RecyclingTipRepository recyclingTipRepository;

    @Mock
    private WasteCategoryRepository wasteCategoryRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private RecyclingTipServiceImp recyclingTipService;

    private RecyclingTip recyclingTip;
    private RecyclingTipDTO recyclingTipDTO;
    private WasteCategory wasteCategory;

    @BeforeEach
    void setUp() {
        recyclingTip = new RecyclingTip();
        recyclingTip.setId(1L);
        recyclingTip.setTip("Recycle plastic waste");

        recyclingTipDTO = new RecyclingTipDTO();
        recyclingTipDTO.setId(1L);
        recyclingTipDTO.setTip("Recycle plastic waste");
        recyclingTipDTO.setCategoryId(1L);

        wasteCategory = new WasteCategory();
        wasteCategory.setId(1L);
        wasteCategory.setName("Plastic");
    }

    @Test
    void testGetAllRecyclingTips() {
        // Mocking repository call
        when(recyclingTipRepository.findAll()).thenReturn(Arrays.asList(recyclingTip));
        when(modelMapper.map(any(RecyclingTip.class), eq(RecyclingTipDTO.class))).thenReturn(recyclingTipDTO);

        // Testing the service method
        var result = recyclingTipService.getAllRecyclingTips();

        // Verifying the result
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Recycle plastic waste", result.get(0).getTip());
        verify(recyclingTipRepository, times(1)).findAll();
    }

    @Test
    void testGetRecyclingTipsByCategory() {
        // Mocking repository call
        when(recyclingTipRepository.findRecyclingTipsByCategoryId(anyLong())).thenReturn(Arrays.asList(recyclingTip));
        when(modelMapper.map(any(RecyclingTip.class), eq(RecyclingTipDTO.class))).thenReturn(recyclingTipDTO);

        // Testing the service method
        var result = recyclingTipService.getRecyclingTipsByCategory(1L);

        // Verifying the result
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Recycle plastic waste", result.get(0).getTip());
        verify(recyclingTipRepository, times(1)).findRecyclingTipsByCategoryId(1L);
    }

    @Test
    void testAddRecyclingTip() {
        // Mocking repository call
        when(modelMapper.map(any(RecyclingTipDTO.class), eq(RecyclingTip.class))).thenReturn(recyclingTip);
        when(recyclingTipRepository.save(any(RecyclingTip.class))).thenReturn(recyclingTip);
        when(modelMapper.map(any(RecyclingTip.class), eq(RecyclingTipDTO.class))).thenReturn(recyclingTipDTO);

        // Testing the service method
        RecyclingTipDTO result = recyclingTipService.addRecyclingTip(recyclingTipDTO);

        // Verifying the result
        assertNotNull(result);
        assertEquals("Recycle plastic waste", result.getTip());
        verify(recyclingTipRepository, times(1)).save(any(RecyclingTip.class));
    }

    @Test
    void testUpdateRecyclingTip_Success() {
        // Create existing category and recycling tip
        WasteCategory existingCategory = new WasteCategory();
        existingCategory.setId(1L);
        existingCategory.setName("Plastic");
        existingCategory.setDescription("Plastic waste");

        RecyclingTip existingTip = new RecyclingTip();
        existingTip.setId(1L);
        existingTip.setTip("Recycle plastic");
        existingTip.setCategory(existingCategory);

        // Mocking repository calls
        when(recyclingTipRepository.findById(anyLong())).thenReturn(Optional.of(existingTip));
        when(wasteCategoryRepository.findById(anyLong())).thenReturn(Optional.of(existingCategory));

        // Prepare the DTO with matching categoryId
        RecyclingTipDTO recyclingTipDTO = new RecyclingTipDTO();
        recyclingTipDTO.setId(1L);
        recyclingTipDTO.setTip("Updated recycling tip");
        recyclingTipDTO.setCategoryId(1L);  // Matching categoryId

        // Call the service method
        RecyclingTipDTO updatedTipDTO = recyclingTipService.updateRecyclingTip(recyclingTipDTO);

        // Assert not null
        assertNotNull(updatedTipDTO, "The updated RecyclingTipDTO should not be null");
        assertEquals("Updated recycling tip", updatedTipDTO.getTip(), "Tip content should match");
        assertEquals(1L, updatedTipDTO.getCategoryId(), "Category ID should match");
    }



    @Test
    void testUpdateRecyclingTip_Fail_CategoryMismatch() {
        // Mocking repository call for RecyclingTip
        RecyclingTip anotherTip = new RecyclingTip();
        anotherTip.setId(1L);
        anotherTip.setTip("Recycle paper waste");
        anotherTip.setCategory(wasteCategory);

        // Mocking the call to findById for RecyclingTip and WasteCategory
        when(recyclingTipRepository.findById(anyLong())).thenReturn(Optional.of(anotherTip));
        when(wasteCategoryRepository.findById(anyLong())).thenReturn(Optional.of(new WasteCategory())); // 'newWasteCategory' should be different from 'existingWasteCategory'

        // Testing the service method
        Enviro365ExceptionHandler thrown = assertThrows(Enviro365ExceptionHandler.class, () -> {
            recyclingTipService.updateRecyclingTip(recyclingTipDTO); // 'recyclingTipDTO' should have 'categoryId' matching 'newWasteCategory'
        });

        // Verifying the exception message
        assertEquals("Disposal Guideline does not belong to Waste Category", thrown.getMessage());
    }

    @Test
    void testGetRecyclingTipById_Success() {
        // Mocking repository call
        when(recyclingTipRepository.findById(anyLong())).thenReturn(Optional.of(recyclingTip));
        when(modelMapper.map(any(RecyclingTip.class), eq(RecyclingTipDTO.class))).thenReturn(recyclingTipDTO);

        // Testing the service method
        RecyclingTipDTO result = recyclingTipService.getRecyclingTipById(1L);

        // Verifying the result
        assertNotNull(result);
        assertEquals("Recycle plastic waste", result.getTip());
        verify(recyclingTipRepository, times(1)).findById(1L);
    }

    @Test
    void testGetRecyclingTipById_NotFound() {
        // Mocking repository call
        when(recyclingTipRepository.findById(anyLong())).thenReturn(Optional.empty());

        // Testing the service method
        ResourceNotFoundException thrown = assertThrows(ResourceNotFoundException.class, () -> {
            recyclingTipService.getRecyclingTipById(1L);
        });

        // Verifying the exception message
        assertEquals("Recycling Tip not found with id : '1'", thrown.getMessage());
    }

    @Test
    void testDeleteRecyclingTipById_Success() {
        // Mocking repository call
        when(recyclingTipRepository.findById(anyLong())).thenReturn(Optional.of(recyclingTip));
        doNothing().when(recyclingTipRepository).delete(any(RecyclingTip.class));

        // Testing the service method
        recyclingTipService.deleteRecyclingTipById(1L);

        // Verifying the repository interaction
        verify(recyclingTipRepository, times(1)).delete(any(RecyclingTip.class));
    }

    @Test
    void testDeleteRecyclingTipById_NotFound() {
        // Mocking repository call
        when(recyclingTipRepository.findById(anyLong())).thenReturn(Optional.empty());

        // Testing the service method
        ResourceNotFoundException thrown = assertThrows(ResourceNotFoundException.class, () -> {
            recyclingTipService.deleteRecyclingTipById(1L);
        });

        // Verifying the exception message
        assertEquals("Recycling Tip not found with id : '1'", thrown.getMessage());
    }
}
