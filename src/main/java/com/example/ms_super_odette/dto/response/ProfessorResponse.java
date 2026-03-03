package com.example.ms_super_odette.dto.response;

import lombok.*;
import java.time.LocalDate;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class ProfessorResponse {
    private Long cdUsuario;
    private String nmProfessor;
    private LocalDate dtContratacao;
}