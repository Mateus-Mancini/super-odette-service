package com.example.ms_super_odette.model;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "nota")
public class Grade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ncdnota")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "ncdaluno", nullable = false)
    private Student student;

    @ManyToOne
    @JoinColumn(name = "ncddisciplina", nullable = false)
    private Subject subject;

    @Column(name = "nvalor", nullable = false, precision = 5, scale = 2)
    private BigDecimal value;

    @Column(name = "dtlancamento", nullable = false)
    private LocalDate date;
}