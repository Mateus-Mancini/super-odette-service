package com.example.superodette.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tipo_usuario")
public class TipoUsuario {

    @Id
    @Column(name = "nCdTipoUsuario")
    private Integer cdTipoUsuario;

    @Column(name = "cDescricao", nullable = false, unique = true, length = 50)
    private String descricao;

}