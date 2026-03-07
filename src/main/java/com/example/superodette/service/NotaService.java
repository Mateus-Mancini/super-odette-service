package com.example.superodette.service;

import com.example.superodette.dto.request.NotaRequest;
import com.example.superodette.dto.response.NotaResponse;
import com.example.superodette.model.Nota;
import com.example.superodette.repository.NotaRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NotaService {

    private final NotaRepository repository;

    private final ObjectMapper objectMapper;

    public List<NotaResponse> listarTodos() {
        return repository.findAll().stream()
                .map(n -> objectMapper.convertValue(n, NotaResponse.class))
                .toList();
    }

    public NotaResponse buscarPorId(Long id) {
        Nota nota = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Nota não encontrada com id: " + id));
        return objectMapper.convertValue(nota, NotaResponse.class);
    }

    @Transactional
    public NotaResponse inserir(NotaRequest request) {
        Nota nota = objectMapper.convertValue(request, Nota.class);
        Nota salvo = repository.save(nota);
        return objectMapper.convertValue(salvo, NotaResponse.class);
    }

    @Transactional
    public NotaResponse atualizar(Long id, NotaRequest request) {
        Nota nota = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Nota não encontrada com id: " + id));
        nota.setCdAluno(request.getCdAluno());
        nota.setCdDisciplina(request.getCdDisciplina());
        nota.setValor(request.getValor());
        nota.setDtLancamento(request.getDtLancamento());
        Nota atualizado = repository.save(nota);
        return objectMapper.convertValue(atualizado, NotaResponse.class);
    }

    @Transactional
    public void remover(Long id) {
        if (!repository.existsById(id)) {
            throw new EntityNotFoundException("Nota não encontrada com id: " + id);
        }
        repository.deleteById(id);
    }
}