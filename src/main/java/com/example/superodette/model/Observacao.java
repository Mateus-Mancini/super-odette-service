package com.example.superodette.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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