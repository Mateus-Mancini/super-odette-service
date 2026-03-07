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
@Table(name = "aluno")
public class Aluno {
    @Id
    @Column(name = "nCdUsuario")
    private Long cdUsuario;

    @Column(name = "cNmAluno", nullable = false, length = 150)
    private String nmAluno;

    @Column(name = "dtNascimento")
    private LocalDate dtNascimento;
}