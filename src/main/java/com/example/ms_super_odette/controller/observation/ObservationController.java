package com.example.ms_super_odette.controller.observation;

import com.example.ms_super_odette.annotation.CurrentUser;
import com.example.ms_super_odette.annotation.RequireRole;
import com.example.ms_super_odette.dto.request.ObservationRequest;
import com.example.ms_super_odette.dto.response.ObservationResponse;
import com.example.ms_super_odette.model.SessionData;
import com.example.ms_super_odette.service.observation.ObservationContext;
import com.example.ms_super_odette.service.observation.SharedObservationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/observations")
@RequiredArgsConstructor
public class ObservationController {

    private final ObservationContext       observationContext;
    private final SharedObservationService sharedObservationService;

    @GetMapping
    public ResponseEntity<List<ObservationResponse>> getObservations(@CurrentUser SessionData user) {
        return ResponseEntity.ok(observationContext.getObservations(user));
    }

    @GetMapping("/student/{studentId}")
    @RequireRole({"PROFESSOR", "SECRETARIA"})
    public ResponseEntity<List<ObservationResponse>> getStudentObservations(
            @PathVariable Long studentId) {
        return ResponseEntity.ok(sharedObservationService.getStudentObservations(studentId));
    }

    @PutMapping({"", "/{id}"})
    @RequireRole({"PROFESSOR", "SECRETARIA"})
    public ResponseEntity<ObservationResponse> upsertObservation(
            @CurrentUser SessionData user,
            @PathVariable(required = false) Long id,
            @Valid @RequestBody ObservationRequest request) {
        ObservationResponse response = sharedObservationService.upsert(user, request, id);
        HttpStatus status = (id == null) ? HttpStatus.CREATED : HttpStatus.OK;
        return ResponseEntity.status(status).body(response);
    }
}