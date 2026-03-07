package com.example.superodette.service;

import com.example.superodette.dto.request.SecretariaRequest;
import com.example.superodette.dto.response.SecretariaResponse;
import com.example.superodette.model.Secretaria;
import com.example.superodette.repository.SecretariaRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SecretariaService {

    private final SecretariaRepository repository;

    private final ObjectMapper objectMapper;

    public List<SecretariaResponse> listarTodos() {
        return repository.findAll().stream()
                .map(s -> objectMapper.convertValue(s, SecretariaResponse.class))
                .toList();
    }

    public SecretariaResponse buscarPorId(Long id) {
        Secretaria secretaria = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Secretaria não encontrada com id: " + id));
        return objectMapper.convertValue(secretaria, SecretariaResponse.class);
    }

    @Transactional
    public SecretariaResponse inserir(SecretariaRequest request) {
        Secretaria secretaria = objectMapper.convertValue(request, Secretaria.class);
        Secretaria salvo = repository.save(secretaria);
        return objectMapper.convertValue(salvo, SecretariaResponse.class);
    }

    @Transactional
    public SecretariaResponse atualizar(Long id, SecretariaRequest request) {
        Secretaria secretaria = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Secretaria não encontrada com id: " + id));
        secretaria.setNmSecretaria(request.getNmSecretaria());
        Secretaria atualizado = repository.save(secretaria);
        return objectMapper.convertValue(atualizado, SecretariaResponse.class);
    }

    @Transactional
    public void remover(Long id) {
        if (!repository.existsById(id)) {
            throw new EntityNotFoundException("Secretaria não encontrada com id: " + id);
        }
        repository.deleteById(id);
    }
}