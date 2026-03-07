package com.example.superodette.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class TipoUsuarioResponse {
    private Integer cdTipoUsuario;
    private String descricao;
}