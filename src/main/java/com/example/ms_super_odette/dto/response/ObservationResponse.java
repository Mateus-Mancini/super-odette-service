package com.example.ms_super_odette.dto.response;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class ObservationResponse {
    private Long cdObservacao;
    private Long cdAluno;
    private String observacao;
    private LocalDateTime dtRegistro;
}