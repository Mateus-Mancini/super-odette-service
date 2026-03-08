package com.example.ms_super_odette.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "turma")
@Getter
@Setter
public class ClassGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ncdturma")
    private Long id;

    @Column(name = "cnmturma", nullable = false)
    private String name;

    @Column(name = "nano", nullable = false)
    private Integer year;
}