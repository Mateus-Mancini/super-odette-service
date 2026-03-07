package com.example.superodette.model;

import jakarta.persistence.Embeddable;
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