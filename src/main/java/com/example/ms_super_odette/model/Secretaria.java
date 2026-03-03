package com.example.ms_super_odette.model;

import jakarta.persistence.*;
import lombok.*;

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