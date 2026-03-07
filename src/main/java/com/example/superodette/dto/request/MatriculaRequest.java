package com.example.superodette.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class MatriculaRequest {
    @NotNull(message = "Código da matrícula é obrigatório")
    private Long cdMatricula;

    @NotNull(message = "Código do aluno é obrigatório")
    private Long cdAluno;

    @NotNull(message = "Código da turma é obrigatório")
    private Long cdTurma;

    @NotNull(message = "Data da matrícula é obrigatória")
    private LocalDate dtMatricula;

    @NotBlank(message = "Status é obrigatório")
    @Size(max = 20)
    private String status;
}