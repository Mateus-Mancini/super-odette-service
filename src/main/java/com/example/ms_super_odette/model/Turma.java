package com.example.ms_super_odette.model;

import jakarta.persistence.*;
import lombok.*;

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