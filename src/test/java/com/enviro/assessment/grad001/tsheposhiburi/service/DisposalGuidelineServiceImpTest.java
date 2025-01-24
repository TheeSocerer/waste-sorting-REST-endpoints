package com.enviro.assessment.grad001.tsheposhiburi.service;

import com.enviro.assessment.grad001.tsheposhiburi.exception.Enviro365ExceptionHandler;
import com.enviro.assessment.grad001.tsheposhiburi.exception.ResourceNotFoundException;
import com.enviro.assessment.grad001.tsheposhiburi.model.DisposalGuideline;
import com.enviro.assessment.grad001.tsheposhiburi.model.WasteCategory;
import com.enviro.assessment.grad001.tsheposhiburi.payload.DisposalGuidelineDTO;
import com.enviro.assessment.grad001.tsheposhiburi.repository.DisposalGuidelineRepository;
import com.enviro.assessment.grad001.tsheposhiburi.repository.WasteCategoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.modelmapper.ModelMapper;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class DisposalGuidelineServiceImpTest {

    @InjectMocks
    private DisposalGuidelineServiceImp disposalGuidelineService;

    @Mock
    private DisposalGuidelineRepository disposalGuidelineRepository;

    @Mock
    private WasteCategoryRepository wasteCategoryRepository;

    @Mock
    private ModelMapper mapper;

    private DisposalGuideline disposalGuideline;
    private DisposalGuidelineDTO disposalGuidelineDTO;
    private WasteCategory wasteCategory;

    @BeforeEach
    void setUp() {
        wasteCategory = new WasteCategory();
        wasteCategory.setId(1L);
        wasteCategory.setName("Plastic");
        disposalGuideline = new DisposalGuideline(1L, "Recycle plastic", wasteCategory);
        disposalGuidelineDTO = new DisposalGuidelineDTO(1L, "Recycle plastic", 1L);
    }

    @Test
    void testGetDisposalGuidelines() {
        // Arrange
        when(disposalGuidelineRepository.findAll()).thenReturn(List.of(disposalGuideline));
        when(mapper.map(disposalGuideline, DisposalGuidelineDTO.class)).thenReturn(disposalGuidelineDTO);

        // Act
        List<DisposalGuidelineDTO> result = disposalGuidelineService.getDisposalGuidelines();

        // Assert
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(disposalGuidelineDTO.getGuideline(), result.get(0).getGuideline());
    }

    @Test
    void testGetDisposalGuidelinesByCategoryId() {
        // Arrange
        when(disposalGuidelineRepository.findDisposalGuidelinesByCategoryId(1L)).thenReturn(List.of(disposalGuideline));
        when(mapper.map(disposalGuideline, DisposalGuidelineDTO.class)).thenReturn(disposalGuidelineDTO);

        // Act
        List<DisposalGuidelineDTO> result = disposalGuidelineService.getDisposalGuidelinesByCategoryId(1L);

        // Assert
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(disposalGuidelineDTO.getGuideline(), result.get(0).getGuideline());
    }

    @Test
    void testAddDisposalGuideline() {
        // Arrange
        when(wasteCategoryRepository.findById(1L)).thenReturn(Optional.of(wasteCategory));
        when(mapper.map(disposalGuidelineDTO, DisposalGuideline.class)).thenReturn(disposalGuideline);
        when(disposalGuidelineRepository.save(disposalGuideline)).thenReturn(disposalGuideline);
        when(mapper.map(disposalGuideline, DisposalGuidelineDTO.class)).thenReturn(disposalGuidelineDTO);

        // Act
        DisposalGuidelineDTO result = disposalGuidelineService.addDisposalGuideline(disposalGuidelineDTO);

        // Assert
        assertNotNull(result);
        assertEquals(disposalGuidelineDTO.getGuideline(), result.getGuideline());
    }

    @Test
    void testGetDisposalGuidelineById() {
        // Arrange
        when(disposalGuidelineRepository.findById(1L)).thenReturn(Optional.of(disposalGuideline));
        when(mapper.map(disposalGuideline, DisposalGuidelineDTO.class)).thenReturn(disposalGuidelineDTO);

        // Act
        DisposalGuidelineDTO result = disposalGuidelineService.getDisposalGuidelineById(1L);

        // Assert
        assertNotNull(result);
        assertEquals(disposalGuidelineDTO.getGuideline(), result.getGuideline());
    }

    @Test
    void testUpdateDisposalGuideline() {
        // Arrange
        when(disposalGuidelineRepository.findById(1L)).thenReturn(Optional.of(disposalGuideline));
        when(wasteCategoryRepository.findById(1L)).thenReturn(Optional.of(wasteCategory));
        when(disposalGuidelineRepository.save(disposalGuideline)).thenReturn(disposalGuideline);
        when(mapper.map(disposalGuideline, DisposalGuidelineDTO.class)).thenReturn(disposalGuidelineDTO);

        // Act
        DisposalGuidelineDTO result = disposalGuidelineService.updateDisposalGuideline(disposalGuidelineDTO);

        // Assert
        assertNotNull(result);
        assertEquals(disposalGuidelineDTO.getGuideline(), result.getGuideline());
    }
    @Test
    void testUpdateDisposalGuidelineWithInvalidCategory() {
        // Arrange
        when(disposalGuidelineRepository.findById(1L)).thenReturn(Optional.of(disposalGuideline));
        when(wasteCategoryRepository.findById(2L)).thenReturn(Optional.empty()); // Simulate invalid category ID

        // Act & Assert for ResourceNotFoundException (if category ID is not found)
        assertThrows(ResourceNotFoundException.class, () ->
                disposalGuidelineService.updateDisposalGuideline(new DisposalGuidelineDTO(1L, "New Guideline", 2L))
        );

        // Act & Assert for Enviro365ExceptionHandler (if the category ID doesn't match)
        WasteCategory wasteCategory1 = new WasteCategory();
        wasteCategory1.setId(2L);
        wasteCategory1.setName("Glass");
        when(wasteCategoryRepository.findById(2L)).thenReturn(Optional.of(wasteCategory1));
        assertThrows(Enviro365ExceptionHandler.class, () ->
                disposalGuidelineService.updateDisposalGuideline(new DisposalGuidelineDTO(1L, "New Guideline", 2L))
        );
    }


    @Test
    void testDeleteDisposalGuidelineById() {
        // Arrange
        when(disposalGuidelineRepository.findById(1L)).thenReturn(Optional.of(disposalGuideline));

        // Act
        disposalGuidelineService.deleteDisposalGuidelineById(1L);

        // Assert
        verify(disposalGuidelineRepository, times(1)).delete(disposalGuideline);
    }

    @Test
    void testDeleteDisposalGuidelineByIdNotFound() {
        // Arrange
        when(disposalGuidelineRepository.findById(1L)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(ResourceNotFoundException.class, () -> disposalGuidelineService.deleteDisposalGuidelineById(1L));
    }
}
