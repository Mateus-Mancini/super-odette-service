package com.example.superodette.dto.request;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class NotaRequest {
    @NotNull(message = "Código da nota é obrigatório")
    private Long cdNota;

    @NotNull(message = "Código do aluno é obrigatório")
    private Long cdAluno;

    @NotNull(message = "Código da disciplina é obrigatório")
    private Long cdDisciplina;

    @NotNull(message = "Valor da nota é obrigatório")
    @DecimalMin(value = "0.0", inclusive = true, message = "Nota mínima é 0")
    @DecimalMax(value = "10.0", inclusive = true, message = "Nota máxima é 10")
    private BigDecimal valor;

    @NotNull(message = "Data de lançamento é obrigatória")
    private LocalDate dtLancamento;
}