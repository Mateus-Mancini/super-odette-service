package com.example.superodette.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class DisciplinaRequest {
    @NotNull(message = "Código da disciplina é obrigatório")
    private Long cdDisciplina;

    @NotBlank(message = "Nome da disciplina é obrigatório")
    @Size(max = 100)
    private String nmDisciplina;
}