package com.example.ms_super_odette.dto.response;

import lombok.*;
import java.time.LocalDate;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class MatriculaResponse {
    private Long cdMatricula;
    private Long cdAluno;
    private Long cdTurma;
    private LocalDate dtMatricula;
    private String status;
}