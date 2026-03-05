package com.example.ms_super_odette.auth.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

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