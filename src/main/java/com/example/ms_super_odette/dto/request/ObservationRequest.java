package com.example.ms_super_odette.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ObservationRequest {
    private Long cdObservacao; // se nulo, insere; se presente, atualiza
    @NotNull(message = "Código do aluno é obrigatório")
    private Long cdAluno;
    @Size(max = 500)
    private String observacao;
}