package com.example.ms_super_odette.model;

import jakarta.persistence.*;
import lombok.*;

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