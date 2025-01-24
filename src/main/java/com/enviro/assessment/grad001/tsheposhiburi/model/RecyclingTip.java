package com.enviro.assessment.grad001.tsheposhiburi.model;

import jakarta.persistence.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "recyclingtips")
public class RecyclingTip {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "tip")
    private String tip;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "waste_category_id")
    private WasteCategory category;
}
