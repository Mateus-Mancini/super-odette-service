package com.example.superodette.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ObservacaoRequest {
    @NotNull(message = "Código da observação é obrigatório")
    private Long cdObservacao;

    @NotNull(message = "Código do aluno é obrigatório")
    private Long cdAluno;

    @Size(max = 500, message = "Observação deve ter no máximo 500 caracteres")
    private String observacao;

    private LocalDateTime dtRegistro;
}