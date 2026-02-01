package com.aiplatform.analytics_service.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Table(name = "daily_summary")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DailySummary {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String userEmail;
    private String domain;

    private Integer totalCount;
    private Double totalValue;

    private LocalDate date;
}
