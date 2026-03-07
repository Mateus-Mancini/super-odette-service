package com.example.superodette.dto.request;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class UsuarioRequest {
    @NotNull(message = "Código do usuário é obrigatório")
    private Long cdUsuario;

    @NotNull(message = "Tipo de usuário é obrigatório")
    private Integer cdTipoUsuario;

    @NotBlank(message = "E-mail é obrigatório")
    @Email(message = "E-mail inválido")
    @Size(max = 150)
    private String email;

    @NotBlank(message = "Senha é obrigatória")
    @Size(max = 255)
    private String senha;

    private LocalDateTime dtCriacao;   // será ignorado na inserção (usar default do banco)
    private LocalDateTime dtAtualizacao;
}