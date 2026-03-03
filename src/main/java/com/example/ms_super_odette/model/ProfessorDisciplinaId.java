package com.example.ms_super_odette.model;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Entity;
import lombok.*;
import java.io.Serializable;

@Embeddable
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class ProfessorDisciplinaId implements Serializable {
    private Long cdProfessor;
    private Long cdDisciplina;
}