package com.enviro.assessment.grad001.tsheposhiburi.model;

import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "disposalguidelines")
public class DisposalGuideline {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "guideline")
    private String guideline;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "waste_category_id")
    private WasteCategory category;
}
