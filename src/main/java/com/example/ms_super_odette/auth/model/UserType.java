package com.example.ms_super_odette.auth.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "tipo_usuario")
@Getter
@Setter
public class UserType {

    @Id
    @Column(name = "ncdtipousuario")
    private Integer id;

    @Column(name = "cdescricao", nullable = false, unique = true, length = 50)
    private String description;
}