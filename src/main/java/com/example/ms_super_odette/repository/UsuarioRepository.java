package com.example.ms_super_odette.repository;

import com.example.ms_super_odette.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}