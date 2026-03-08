package com.example.ms_super_odette.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class EnrollmentApprovalRequest {

    @NotNull(message = "O ID da turma é obrigatório para aprovar a matrícula.")
    private Long classGroupId;
}