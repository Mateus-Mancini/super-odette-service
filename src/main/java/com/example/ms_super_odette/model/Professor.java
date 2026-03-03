package com.example.ms_super_odette.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "professor")
public class Professor {
    @Id
    @Column(name = "nCdUsuario")
    private Long cdUsuario;

    @Column(name = "cNmProfessor", nullable = false, length = 150)
    private String nmProfessor;

    @Column(name = "dtContratacao")
    private LocalDate dtContratacao;
}