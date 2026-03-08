package com.example.ms_super_odette.model;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "professor_disciplina")
public class TeacherSubject {

    @EmbeddedId
    private TeacherSubjectId id;

    @ManyToOne
    @MapsId("teacher")
    @JoinColumn(name = "ncdprofessor")
    private Teacher teacher;

    @ManyToOne
    @MapsId("subject")
    @JoinColumn(name = "ncddisciplina")
    private Subject subject;
}