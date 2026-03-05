package com.example.ms_super_odette.enrollment.model;

import com.example.superodette.auth.model.Student;
import com.example.superodette.classgroup.model.ClassGroup;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "matricula")
@Getter
@Setter
public class Enrollment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ncdmatricula")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "ncdaluno", nullable = false)
    private Student student;

    @ManyToOne
    @JoinColumn(name = "ncdturma", nullable = false)
    private ClassGroup classGroup;

    @Column(name = "dtmatricula", nullable = false)
    private java.time.LocalDate enrolledAt;

    @Column(name = "istatus", nullable = false)
    private String status;
}