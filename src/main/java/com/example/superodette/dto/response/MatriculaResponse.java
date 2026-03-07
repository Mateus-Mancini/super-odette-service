package com.example.superodette.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class MatriculaResponse {
    private Long cdMatricula;
    private Long cdAluno;
    private Long cdTurma;
    private LocalDate dtMatricula;
    private String status;
}