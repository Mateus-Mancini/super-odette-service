package com.example.ms_super_odette.service.observation;

import com.example.ms_super_odette.dto.response.ObservationResponse;
import com.example.ms_super_odette.model.SessionData;
import com.example.ms_super_odette.repository.ObservationRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class StudentObservationStrategy implements ObservationStrategy {

    private final ObservationRepository observationRepo;
    private final ObservationMapper mapper;

    @Override
    public boolean supports(SessionData user) {
        return user.getRoles().contains("ALUNO");
    }

    @Override
    public List<ObservationResponse> getObservations(SessionData user) {
        return mapper.toStudentResponseList(
                observationRepo.findByStudentId(user.getUserId())
        );
    }
}