package com.example.superodette.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class SecretariaRequest {
    @NotNull(message = "Código do usuário é obrigatório")
    private Long cdUsuario;

    @NotBlank(message = "Nome da secretaria é obrigatório")
    @Size(max = 150)
    private String nmSecretaria;
}