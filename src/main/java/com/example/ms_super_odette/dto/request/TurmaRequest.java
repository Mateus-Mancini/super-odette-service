package com.example.ms_super_odette.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class TurmaRequest {
    @NotNull(message = "Código da turma é obrigatório")
    private Long cdTurma;

    @NotBlank(message = "Nome da turma é obrigatório")
    @Size(max = 100)
    private String nmTurma;

    @NotNull(message = "Ano é obrigatório")
    private Integer ano;
}