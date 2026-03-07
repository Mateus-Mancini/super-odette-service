package com.example.ms_super_odette.model;

import jakarta.persistence.*;
import lombok.*;

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