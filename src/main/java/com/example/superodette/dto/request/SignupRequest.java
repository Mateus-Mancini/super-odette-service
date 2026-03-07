package com.example.superodette.dto.request;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDate;

@Data
public class SignupRequest {
    @NotNull(message = "Código do usuário é obrigatório")
    private Long cdUsuario;

    @NotBlank(message = "E-mail é obrigatório")
    @Email(message = "E-mail inválido")
    private String email;

    @NotBlank(message = "Senha é obrigatória")
    @Size(min = 6, message = "A senha deve ter no mínimo 6 caracteres")
    private String senha;

    @NotNull(message = "Tipo de usuário é obrigatório")
    private Integer cdTipoUsuario;

    @NotBlank(message = "Nome é obrigatório")
    private String nome;

    private LocalDate dtNascimento; // para aluno
    private LocalDate dtContratacao; // para professor
    // secretaria não tem data extra
}