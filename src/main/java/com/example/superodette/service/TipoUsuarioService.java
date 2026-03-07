package com.example.superodette.service;

import com.example.superodette.dto.request.TipoUsuarioRequest;
import com.example.superodette.dto.response.TipoUsuarioResponse;
import com.example.superodette.model.TipoUsuario;
import com.example.superodette.repository.TipoUsuarioRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TipoUsuarioService {

    private final TipoUsuarioRepository repository;

    private final ObjectMapper objectMapper;

    public List<TipoUsuarioResponse> listarTodos() {
        return repository.findAll().stream()
                .map(t -> objectMapper.convertValue(t, TipoUsuarioResponse.class))
                .toList();
    }

    public TipoUsuarioResponse buscarPorId(Integer id) {
        TipoUsuario tipo = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("TipoUsuario não encontrado com id: " + id));
        return objectMapper.convertValue(tipo, TipoUsuarioResponse.class);
    }

    @Transactional
    public TipoUsuarioResponse inserir(TipoUsuarioRequest request) {
        TipoUsuario tipo = objectMapper.convertValue(request, TipoUsuario.class);
        TipoUsuario salvo = repository.save(tipo);
        return objectMapper.convertValue(salvo, TipoUsuarioResponse.class);
    }

    @Transactional
    public TipoUsuarioResponse atualizar(Integer id, TipoUsuarioRequest request) {
        TipoUsuario tipo = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("TipoUsuario não encontrado com id: " + id));
        tipo.setDescricao(request.getDescricao());
        TipoUsuario atualizado = repository.save(tipo);
        return objectMapper.convertValue(atualizado, TipoUsuarioResponse.class);
    }

    @Transactional
    public void remover(Integer id) {
        if (!repository.existsById(id)) {
            throw new EntityNotFoundException("TipoUsuario não encontrado com id: " + id);
        }
        repository.deleteById(id);
    }
}