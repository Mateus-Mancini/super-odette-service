package com.example.ms_super_odette.dto.response;

import lombok.*;
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