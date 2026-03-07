package com.example.superodette.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "grade")
public class Grade {
    @Id
    @Column(name = "nCdGrade")
    private Long cdGrade;

    @Column(name = "nCdTurma", nullable = false)
    private Long cdTurma;

    @Column(name = "nCdDisciplina", nullable = false)
    private Long cdDisciplina;

    @Column(name = "nCdProfessor", nullable = false)
    private Long cdProfessor;

    @Column(name = "tHrInicio", nullable = false)
    private LocalTime hrInicio;

    @Column(name = "tHrFim", nullable = false)
    private LocalTime hrFim;

    @Column(name = "iDiaSemana", nullable = false)
    private Integer diaSemana; // 1=domingo, 2=segunda, ..., 7=sábado
}