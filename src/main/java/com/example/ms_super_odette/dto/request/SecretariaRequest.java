package com.example.ms_super_odette.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class SecretariaRequest {
    @NotNull(message = "Código do usuário é obrigatório")
    private Long cdUsuario;

    @NotBlank(message = "Nome da secretaria é obrigatório")
    @Size(max = 150)
    private String nmSecretaria;
}