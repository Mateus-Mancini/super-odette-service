package com.example.ms_super_odette.dto.request;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
public class GradeRequest {

    @NotNull(message = "O ID do aluno é obrigatório.")
    private Long studentId;

    @NotNull(message = "O ID da disciplina é obrigatório.")
    private Long subjectId;

    @DecimalMin(value = "0.0", message = "A nota deve ser no mínimo 0.")
    @DecimalMax(value = "10.0", message = "A nota deve ser no máximo 10.")
    private BigDecimal firstSemester;

    @DecimalMin(value = "0.0", message = "A nota deve ser no mínimo 0.")
    @DecimalMax(value = "10.0", message = "A nota deve ser no máximo 10.")
    private BigDecimal secondSemester;
}