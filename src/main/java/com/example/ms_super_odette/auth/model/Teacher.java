package com.example.ms_super_odette.auth.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "professor")
@Getter
@Setter
public class Teacher {

    @Id
    @Column(name = "ncdusuario")
    private Long id;

    @OneToOne
    @MapsId
    @JoinColumn(name = "ncdusuario")
    private User user;

    @Column(name = "dtcontratacao")
    private LocalDate hireDate;
}