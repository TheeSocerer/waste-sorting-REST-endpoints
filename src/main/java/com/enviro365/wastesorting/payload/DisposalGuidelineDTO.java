package com.enviro365.wastesorting.payload;


import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

/*
@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String guideline;
    @ManyToOne
    private WasteCategory category;
 */
@Data
public class DisposalGuidelineDTO {
    private long id;

    @NotEmpty
    @Size(min = 10, message = "Disposal guidelines should have at least 10 characters")
    private String guideline;

    @NotEmpty
    private long categoryId;



}
