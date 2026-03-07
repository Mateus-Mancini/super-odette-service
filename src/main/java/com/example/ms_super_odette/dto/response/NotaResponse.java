package com.example.ms_super_odette.dto.response;

import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class NotaResponse {
    private Long cdNota;
    private Long cdAluno;
    private Long cdDisciplina;
    private BigDecimal valor;
    private LocalDate dtLancamento;
}