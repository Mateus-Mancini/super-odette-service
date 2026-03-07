package com.example.superodette.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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