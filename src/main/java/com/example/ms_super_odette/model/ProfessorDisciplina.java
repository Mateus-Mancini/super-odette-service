package com.example.ms_super_odette.model;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "professor_disciplina")
public class ProfessorDisciplina {
    @EmbeddedId
    private ProfessorDisciplinaId id;
}