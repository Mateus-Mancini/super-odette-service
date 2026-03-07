package com.example.superodette.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class UsuarioResponse {
    private Long cdUsuario;
    private Integer cdTipoUsuario;
    private String email;
    private String senha;
    private LocalDateTime dtCriacao;
    private LocalDateTime dtAtualizacao;
}