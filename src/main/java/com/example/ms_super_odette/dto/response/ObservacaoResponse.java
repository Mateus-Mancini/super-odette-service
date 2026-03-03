package com.example.ms_super_odette.dto.response;

import lombok.*;
import java.time.LocalDateTime;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class ObservacaoResponse {
    private Long cdObservacao;
    private Long cdAluno;
    private String observacao;
    private LocalDateTime dtRegistro;
}