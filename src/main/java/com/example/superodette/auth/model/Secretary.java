package com.example.superodette.auth.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "secretaria")
@Getter
@Setter
public class Secretary {

    @Id
    @Column(name = "ncdusuario")
    private Long id;

    @OneToOne
    @MapsId
    @JoinColumn(name = "ncdusuario")
    private User user;
}