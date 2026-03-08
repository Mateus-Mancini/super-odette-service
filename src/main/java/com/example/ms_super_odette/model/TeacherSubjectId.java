package com.example.ms_super_odette.model;

import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Embeddable
public class TeacherSubjectId implements Serializable {

    private Long teacher;
    private Long subject;
}