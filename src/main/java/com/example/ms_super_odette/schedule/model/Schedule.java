package com.example.ms_super_odette.schedule.model;

import com.example.superodette.auth.model.User;
import com.example.superodette.classgroup.model.ClassGroup;
import com.example.superodette.subject.model.Subject;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;


@Entity
@Table(name = "grade")
@Getter
@Setter
public class Schedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ncdgrade")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "ncdturma", nullable = false)
    private ClassGroup classGroup;

    @ManyToOne
    @JoinColumn(name = "ncddisciplina", nullable = false)
    private Subject subject;

    @ManyToOne
    @JoinColumn(name = "ncdprofessor", nullable = false)
    private User teacher;

    @Column(name = "thrinicio", nullable = false)
    private LocalTime startTime;

    @Column(name = "thrfim", nullable = false)
    private LocalTime endTime;

    @Column(name = "idiasemana", nullable = false)
    private Integer dayOfWeek;
}