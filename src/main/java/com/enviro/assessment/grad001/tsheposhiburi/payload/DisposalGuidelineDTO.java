package com.enviro.assessment.grad001.tsheposhiburi.payload;


import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class DisposalGuidelineDTO {
    private long id;

    @NotEmpty
    @Size(min = 10, message = "Disposal guidelines should have at least 10 characters")
    private String guideline;

    @NotEmpty
    private long categoryId;



}
