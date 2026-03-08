package com.example.ms_super_odette.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tipo_usuario")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ncdtipousuario")
    private Integer id;

    @Column(name = "cdescricao", nullable = false, unique = true, length = 50)
    private String description;
}