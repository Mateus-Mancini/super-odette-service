package com.example.superodette.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "matricula")
public class Matricula {
    @Id
    @Column(name = "nCdMatricula")
    private Long cdMatricula;

    @Column(name = "nCdAluno", nullable = false)
    private Long cdAluno;

    @Column(name = "nCdTurma", nullable = false)
    private Long cdTurma;

    @Column(name = "dtMatricula", nullable = false)
    private LocalDate dtMatricula;

    @Column(name = "iStatus", nullable = false, length = 20)
    private String status;
}