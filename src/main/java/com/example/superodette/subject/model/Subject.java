package com.example.superodette.subject.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "disciplina")
public class Subject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ncddisciplina")
    private Long id;

    @Column(name = "cnmdisciplina", nullable = false)
    private String name;
}