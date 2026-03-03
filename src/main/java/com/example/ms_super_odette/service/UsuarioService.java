package com.example.ms_super_odette.service;

import com.example.ms_super_odette.dto.request.UsuarioRequest;
import com.example.ms_super_odette.dto.response.UsuarioResponse;
import com.example.ms_super_odette.model.Usuario;
import com.example.ms_super_odette.repository.UsuarioRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository repository;

    private final ObjectMapper objectMapper;

    public List<UsuarioResponse> listarTodos() {
        return repository.findAll().stream()
                .map(u -> objectMapper.convertValue(u, UsuarioResponse.class))
                .toList();
    }

    public UsuarioResponse buscarPorId(Long id) {
        Usuario usuario = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado com id: " + id));
        return objectMapper.convertValue(usuario, UsuarioResponse.class);
    }

    @Transactional
    public UsuarioResponse inserir(UsuarioRequest request) {
        Usuario usuario = objectMapper.convertValue(request, Usuario.class);
        usuario.setDtCriacao(LocalDateTime.now());
        usuario.setDtAtualizacao(null);
        Usuario salvo = repository.save(usuario);
        return objectMapper.convertValue(salvo, UsuarioResponse.class);
    }

    @Transactional
    public UsuarioResponse atualizar(Long id, UsuarioRequest request) {
        Usuario usuario = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado com id: " + id));
        usuario.setCdTipoUsuario(request.getCdTipoUsuario());
        usuario.setEmail(request.getEmail());
        usuario.setSenha(request.getSenha());
        usuario.setDtAtualizacao(LocalDateTime.now());
        Usuario atualizado = repository.save(usuario);
        return objectMapper.convertValue(atualizado, UsuarioResponse.class);
    }

    @Transactional
    public void remover(Long id) {
        if (!repository.existsById(id)) {
            throw new EntityNotFoundException("Usuário não encontrado com id: " + id);
        }
        repository.deleteById(id);
    }
}