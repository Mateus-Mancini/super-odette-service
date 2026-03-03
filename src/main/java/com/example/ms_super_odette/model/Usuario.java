package com.example.ms_super_odette.model;

import jakarta.persistence.*;
import lombok.*;
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