package com.example.ms_super_odette.service;

import com.example.ms_super_odette.dto.request.ObservacaoRequest;
import com.example.ms_super_odette.dto.response.ObservacaoResponse;
import com.example.ms_super_odette.model.Observacao;
import com.example.ms_super_odette.repository.ObservacaoRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ObservacaoService {

    private final ObservacaoRepository repository;

    private final ObjectMapper objectMapper;

    public List<ObservacaoResponse> listarTodos() {
        return repository.findAll().stream()
                .map(o -> objectMapper.convertValue(o, ObservacaoResponse.class))
                .toList();
    }

    public ObservacaoResponse buscarPorId(Long id) {
        Observacao observacao = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Observação não encontrada com id: " + id));
        return objectMapper.convertValue(observacao, ObservacaoResponse.class);
    }

    @Transactional
    public ObservacaoResponse inserir(ObservacaoRequest request) {
        Observacao observacao = objectMapper.convertValue(request, Observacao.class);
        observacao.setDtRegistro(LocalDateTime.now());
        Observacao salvo = repository.save(observacao);
        return objectMapper.convertValue(salvo, ObservacaoResponse.class);
    }

    @Transactional
    public ObservacaoResponse atualizar(Long id, ObservacaoRequest request) {
        Observacao observacao = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Observação não encontrada com id: " + id));
        observacao.setCdAluno(request.getCdAluno());
        observacao.setObservacao(request.getObservacao());
        // dtRegistro não deve ser alterada
        Observacao atualizado = repository.save(observacao);
        return objectMapper.convertValue(atualizado, ObservacaoResponse.class);
    }

    @Transactional
    public void remover(Long id) {
        if (!repository.existsById(id)) {
            throw new EntityNotFoundException("Observação não encontrada com id: " + id);
        }
        repository.deleteById(id);
    }
}