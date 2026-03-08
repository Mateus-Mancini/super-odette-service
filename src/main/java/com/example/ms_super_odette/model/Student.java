package com.example.ms_super_odette.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;


@Entity
@Table(name = "aluno")
@Getter
@Setter
public class Student {

    @Id
    @Column(name = "ncdusuario")
    private Long id;

    @OneToOne
    @MapsId
    @JoinColumn(name = "ncdusuario")
    private User user;

    @Column(name = "dtnascimento")
    private LocalDate birthDate;
}