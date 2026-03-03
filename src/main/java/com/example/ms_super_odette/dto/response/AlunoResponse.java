package com.example.ms_super_odette.dto.response;

import lombok.*;
import java.time.LocalDate;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class AlunoResponse {
    private Long cdUsuario;
    private String nmAluno;
    private LocalDate dtNascimento;
}