package com.example.ms_super_odette.service.observation;

import com.example.ms_super_odette.dto.request.ObservationRequest;
import com.example.ms_super_odette.dto.response.ObservationResponse;
import com.example.ms_super_odette.model.Observation;
import com.example.ms_super_odette.model.SessionData;
import com.example.ms_super_odette.model.Student;
import com.example.ms_super_odette.repository.ObservationRepository;
import com.example.ms_super_odette.repository.StudentRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@AllArgsConstructor
public class SharedObservationService {

    private final ObservationRepository observationRepo;
    private final StudentRepository studentRepo;
    private final ObservationMapper mapper;

    public List<ObservationResponse> getStudentObservations(Long studentId) {
        Student student = findStudentOrThrow(studentId);
        return mapper.toResponseList(
                observationRepo.findByStudentId(studentId),
                studentId,
                student.getUser().getName()
        );
    }

    public ObservationResponse upsert(SessionData requester, ObservationRequest request,
                                      Long observationId) {
        Student student = findStudentOrThrow(request.getStudentId());

        Observation observation = (observationId != null)
                ? observationRepo.findById(observationId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Observação não encontrada."))
                : new Observation();

        if (observationId != null && !observation.getStudent().getId().equals(request.getStudentId())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT,
                    "A observação não pertence ao aluno informado.");
        }

        observation.setStudent(student);
        observation.setText(request.getText());
        Observation saved = observationRepo.save(observation);

        return mapper.toResponse(saved, student.getId(), student.getUser().getName());
    }

    private Student findStudentOrThrow(Long studentId) {
        return studentRepo.findById(studentId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Aluno não encontrado."));
    }
}