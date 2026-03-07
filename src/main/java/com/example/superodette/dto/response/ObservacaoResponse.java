package com.example.superodette.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class ObservacaoResponse {
    private Long cdObservacao;
    private Long cdAluno;
    private String observacao;
    private LocalDateTime dtRegistro;
}