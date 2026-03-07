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
public class Nota {
    @Id
    @Column(name = "nCdNota")
    private Long cdNota;

    @Column(name = "nCdAluno", nullable = false)
    private Long cdAluno;

    @Column(name = "nCdDisciplina", nullable = false)
    private Long cdDisciplina;

    @Column(name = "nValor", nullable = false, precision = 5, scale = 2)
    private BigDecimal valor;

    @Column(name = "dtLancamento", nullable = false)
    private LocalDate dtLancamento;
}