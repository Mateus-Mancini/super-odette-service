package com.example.ms_super_odette.model;

import jakarta.persistence.*;
import lombok.*;
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