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
@Table(name = "disciplina")
public class Disciplina {
    @Id
    @Column(name = "nCdDisciplina")
    private Long cdDisciplina;

    @Column(name = "cNmDisciplina", nullable = false, unique = true, length = 100)
    private String nmDisciplina;
}