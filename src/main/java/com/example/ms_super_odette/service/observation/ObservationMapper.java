package com.example.ms_super_odette.service.observation;

import com.example.ms_super_odette.dto.response.ObservationResponse;
import com.example.ms_super_odette.model.Observation;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ObservationMapper {

    public ObservationResponse toResponse(Observation observation, Long studentId, String studentName) {
        return new ObservationResponse(
                observation.getId(),
                observation.getText(),
                observation.getRegisteredAt(),
                studentId,
                studentName
        );
    }

    public ObservationResponse toStudentResponse(Observation observation) {
        return new ObservationResponse(
                observation.getId(),
                observation.getText(),
                observation.getRegisteredAt(),
                null,
                null
        );
    }

    public List<ObservationResponse> toStudentResponseList(List<Observation> observations) {
        return observations.stream()
                .map(this::toStudentResponse)
                .toList();
    }

    public List<ObservationResponse> toResponseList(List<Observation> observations,
                                                    Long studentId, String studentName) {
        return observations.stream()
                .map(o -> toResponse(o, studentId, studentName))
                .toList();
    }
}