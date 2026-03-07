package com.example.ms_super_odette.factory;

import com.example.ms_super_odette.dto.request.SignupRequest;
import com.example.ms_super_odette.model.*;
import com.example.ms_super_odette.repository.TipoUsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserFactory {

    private final TipoUsuarioRepository tipoUsuarioRepository;

    public Usuario createUsuario(SignupRequest request, String senhaCriptografada) {
        TipoUsuario tipo = tipoUsuarioRepository.findById(request.getCdTipoUsuario())
                .orElseThrow(() -> new IllegalArgumentException("Tipo de usuário inválido"));

        Usuario usuario = new Usuario();
        usuario.setCdUsuario(request.getCdUsuario());
        usuario.setCdTipoUsuario(tipo.getCdTipoUsuario());
        usuario.setEmail(request.getEmail());
        usuario.setSenha(senhaCriptografada);
        usuario.setDtCriacao(java.time.LocalDateTime.now());
        return usuario;
    }

    public Aluno createAluno(Usuario usuario, SignupRequest request) {
        Aluno aluno = new Aluno();
        aluno.setCdUsuario(usuario.getCdUsuario());
        aluno.setNmAluno(request.getNome());
        aluno.setDtNascimento(request.getDtNascimento());
        return aluno;
    }

    public Professor createProfessor(Usuario usuario, SignupRequest request) {
        Professor professor = new Professor();
        professor.setCdUsuario(usuario.getCdUsuario());
        professor.setNmProfessor(request.getNome());
        professor.setDtContratacao(request.getDtContratacao());
        return professor;
    }

    public Secretaria createSecretaria(Usuario usuario, SignupRequest request) {
        Secretaria secretaria = new Secretaria();
        secretaria.setCdUsuario(usuario.getCdUsuario());
        secretaria.setNmSecretaria(request.getNome());
        return secretaria;
    }
}