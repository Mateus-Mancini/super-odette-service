package com.example.ms_super_odette.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class TipoUsuarioRequest {
    @NotNull(message = "Código do tipo de usuário é obrigatório")
    private Integer cdTipoUsuario;

    @NotBlank(message = "Descrição é obrigatória")
    @Size(max = 50, message = "Descrição deve ter no máximo 50 caracteres")
    private String descricao;
}