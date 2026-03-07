package com.example.ms_super_odette.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "observacao")
public class Observacao {
    @Id
    @Column(name = "nCdObservacao")
    private Long cdObservacao;

    @Column(name = "nCdAluno", nullable = false)
    private Long cdAluno;

    @Column(name = "cObservacao", length = 500)
    private String observacao;

    @Column(name = "dtRegistro", nullable = false, updatable = false)
    private LocalDateTime dtRegistro;
}