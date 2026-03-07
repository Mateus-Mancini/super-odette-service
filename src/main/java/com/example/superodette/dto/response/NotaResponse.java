package com.example.superodette.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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