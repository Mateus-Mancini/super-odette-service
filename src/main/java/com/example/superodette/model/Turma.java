package com.example.superodette.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "turma")
public class Turma {
    @Id
    @Column(name = "nCdTurma")
    private Long cdTurma;

    @Column(name = "cNmTurma", nullable = false, length = 100)
    private String nmTurma;

    @Column(name = "nAno", nullable = false)
    private Integer ano;
}