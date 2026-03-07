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
@Table(name = "secretaria")
public class Secretaria {
    @Id
    @Column(name = "nCdUsuario")
    private Long cdUsuario;

    @Column(name = "cNmSecretaria", nullable = false, length = 150)
    private String nmSecretaria;
}