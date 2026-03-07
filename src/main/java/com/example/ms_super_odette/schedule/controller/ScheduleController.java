package com.example.ms_super_odette.schedule.controller;

import com.example.ms_super_odette.schedule.service.ScheduleDispatcher;
import com.example.ms_super_odette.auth.annotation.CurrentUser;
import com.example.ms_super_odette.auth.session.SessionData;
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