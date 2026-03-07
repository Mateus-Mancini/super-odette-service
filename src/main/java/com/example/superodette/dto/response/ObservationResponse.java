package com.example.superodette.dto.response;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ObservationResponse {
    private Long cdObservacao;
    private Long cdAluno;
    private String observacao;
    private LocalDateTime dtRegistro;
}