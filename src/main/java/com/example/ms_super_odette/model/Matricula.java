package com.example.ms_super_odette.model;

import jakarta.persistence.*;
import lombok.*;
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