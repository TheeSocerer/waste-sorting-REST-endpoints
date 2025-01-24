package com.enviro.assessment.grad001.tsheposhiburi.payload;


import jakarta.validation.constraints.NotEmpty;
import lombok.*;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class RecyclingTipDTO {
    private Long id;

    @NotEmpty
    private String tip;

    @NotEmpty
    private Long categoryId;


}
