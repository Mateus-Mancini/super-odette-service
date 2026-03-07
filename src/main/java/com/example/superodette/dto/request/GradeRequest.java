package com.example.superodette.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalTime;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class GradeRequest {
    @NotNull(message = "Código da grade é obrigatório")
    private Long cdGrade;

    @NotNull(message = "Código da turma é obrigatório")
    private Long cdTurma;

    @NotNull(message = "Código da disciplina é obrigatório")
    private Long cdDisciplina;

    @NotNull(message = "Código do professor é obrigatório")
    private Long cdProfessor;

    @NotNull(message = "Hora de início é obrigatória")
    private LocalTime hrInicio;

    @NotNull(message = "Hora de fim é obrigatória")
    private LocalTime hrFim;

    @NotNull(message = "Dia da semana é obrigatório (1-7)")
    private Integer diaSemana;
}