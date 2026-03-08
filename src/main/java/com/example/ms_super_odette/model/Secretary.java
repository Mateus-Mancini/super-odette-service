package com.example.ms_super_odette.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "secretaria")
@Getter
@Setter
public class Secretary {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ncdusuario")
    private Long id;

    @OneToOne
    @MapsId
    @JoinColumn(name = "ncdusuario")
    private User user;
}