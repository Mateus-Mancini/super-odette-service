package com.example.ms_super_odette.model;

import com.example.ms_super_odette.enums.EnrollmentStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "matricula")
public class Enrollment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ncdmatricula")
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "ncdaluno", nullable = false)
    private Student student;

    @ManyToOne(optional = true)
    @JoinColumn(name = "ncdturma", nullable = true)
    private ClassGroup classGroup;

    @Column(name = "dtmatricula", nullable = false)
    private LocalDate enrolledAt;

    @Enumerated(EnumType.STRING)
    @Column(name = "istatus", nullable = false, length = 20)
    private EnrollmentStatus status;
}