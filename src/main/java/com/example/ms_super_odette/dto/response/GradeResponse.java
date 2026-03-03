package com.example.ms_super_odette.dto.response;

import lombok.*;
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