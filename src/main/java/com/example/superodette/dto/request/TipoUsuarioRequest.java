package com.example.superodette.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class TipoUsuarioRequest {
    @NotNull(message = "Código do tipo de usuário é obrigatório")
    private Integer cdTipoUsuario;

    @NotBlank(message = "Descrição é obrigatória")
    @Size(max = 50, message = "Descrição deve ter no máximo 50 caracteres")
    private String descricao;
}