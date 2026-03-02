package com.example.superodette.schedule.controller;

import com.example.superodette.auth.annotation.CurrentUser;
import com.example.superodette.auth.session.SessionData;
import com.example.superodette.schedule.service.ScheduleDispatcher;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/schedule")
public class ScheduleController {

    private final ScheduleDispatcher dispatcher;

    public ScheduleController(ScheduleDispatcher dispatcher) {
        this.dispatcher = dispatcher;
    }

    @GetMapping
    public ResponseEntity<?> getSchedule(@CurrentUser SessionData user) {
        return ResponseEntity.ok(dispatcher.getSchedule(user));
    }
}