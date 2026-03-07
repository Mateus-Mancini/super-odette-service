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
@Table(name = "usuario")
public class Usuario {
    @Id
    @Column(name = "nCdUsuario")
    private Long cdUsuario;

    @Column(name = "nCdTipoUsuario", nullable = false)
    private Integer cdTipoUsuario;

    @Column(name = "cEmail", nullable = false, unique = true, length = 150)
    private String email;

    @Column(name = "cSenha", nullable = false, length = 255)
    private String senha;

    @Column(name = "dtCriacao", nullable = false, updatable = false)
    private LocalDateTime dtCriacao;

    @Column(name = "dtAtualizacao")
    private LocalDateTime dtAtualizacao;
}