package com.example.ms_super_odette.controller.schedule;

import com.example.ms_super_odette.annotation.CurrentUser;
import com.example.ms_super_odette.annotation.RequireRole;
import com.example.ms_super_odette.dto.response.ScheduleResponse;
import com.example.ms_super_odette.model.SessionData;
import com.example.ms_super_odette.service.schedule.ScheduleContext;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/schedules")
@RequiredArgsConstructor
public class ScheduleController {

    private final ScheduleContext scheduleContext;

    @GetMapping
    @RequireRole({"ALUNO", "PROFESSOR", "SECRETARIA"})
    public ResponseEntity<List<ScheduleResponse>> getSchedule(
            @CurrentUser SessionData user,
            @RequestParam(required = false) Long teacherId,
            @RequestParam(required = false) Long classGroupId) {
        return ResponseEntity.ok(scheduleContext.getSchedule(user, teacherId, classGroupId));
    }
}