package com.example.ms_super_odette.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "observacao")
public class Observation {
    @Id
    @Column(name = "ncdobservacao")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "ncdaluno", nullable = false)
    private Student student;

    @Column(name = "cobservacao", length = 500)
    private String text;

    @Column(name = "dtregistro", nullable = false, updatable = false)
    private LocalDateTime registeredAt;
}