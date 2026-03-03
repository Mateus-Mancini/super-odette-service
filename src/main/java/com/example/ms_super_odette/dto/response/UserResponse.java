package com.example.ms_super_odette.dto.response;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class UserResponse {
    private Long cdUsuario;
    private String email;
    private Integer cdTipoUsuario;
    private String nome; // nome do perfil (aluno/professor/secretaria)
    private LocalDateTime dtCriacao;
}