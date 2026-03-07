package com.example.superodette.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalTime;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class GradeResponse {
    private Long cdGrade;
    private Long cdTurma;
    private Long cdDisciplina;
    private Long cdProfessor;
    private LocalTime hrInicio;
    private LocalTime hrFim;
    private Integer diaSemana;
}