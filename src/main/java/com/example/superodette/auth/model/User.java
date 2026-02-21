package com.example.superodette.auth.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Entity
@Table(name = "usuario")
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ncdusuario")
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ncdtipousuario", nullable = false)
    private UserType userType;

    @Column(name = "cnome", nullable = false, length = 150)
    private String name;

    @Column(name = "cemail", nullable = false, unique = true, length = 150)
    private String email;

    @Column(name = "csenha", nullable = false, length = 255)
    private String password;

    @Column(name = "dtcriacao", nullable = false, updatable = false)
    private Instant createdAt;

    @Column(name = "dtatualizacao")
    private Instant updatedAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = Instant.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = Instant.now();
    }
}