package com.enviro.assessment.grad001.tsheposhiburi.payload;


import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;


@Data
public class WasteCategoryDTO {
    private Long id;

    // post description should be not null or empty
    @NotEmpty
    private String name;

    // post description should be not null or empty
    @NotEmpty
    @Size(min = 10, message = "Waste category description should have at least 10 characters")
    public String description;
}
