package com.example.ms_super_odette.service.observation;

import com.example.ms_super_odette.dto.response.ObservationResponse;
import com.example.ms_super_odette.model.SessionData;
import org.springframework.aot.hint.annotation.RegisterReflectionForBinding;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ObservationContext {

    private final List<ObservationStrategy> strategies;

    public ObservationContext(List<ObservationStrategy> strategies) {
        this.strategies = strategies;
    }

    @RegisterReflectionForBinding(ObservationResponse.class)
    public List<ObservationResponse> getObservations(SessionData user) {
        return strategies.stream()
                .filter(s -> s.supports(user))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("No observation strategy found for user role."))
                .getObservations(user);
    }
}