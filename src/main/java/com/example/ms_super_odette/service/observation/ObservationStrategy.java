package com.example.ms_super_odette.service.observation;

import com.example.ms_super_odette.dto.response.ObservationResponse;
import com.example.ms_super_odette.model.SessionData;

import java.util.List;

public interface ObservationStrategy {
    boolean supports(SessionData user);
    List<ObservationResponse> getObservations(SessionData user);
}