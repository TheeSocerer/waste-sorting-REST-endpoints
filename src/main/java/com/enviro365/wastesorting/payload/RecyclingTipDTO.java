package com.enviro365.wastesorting.payload;


import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

/**
 * @Id
 *     @GeneratedValue(strategy = GenerationType.IDENTITY)
 *     private Long id;
 *     private String tip;
 *
 *     @ManyToOne
 *     private WasteCategory category;
 */
@Data
public class RecyclingTipDTO {
    private long id;

    @NotEmpty
    private String tip;

    @NotEmpty
    private Long categoryId;

}
