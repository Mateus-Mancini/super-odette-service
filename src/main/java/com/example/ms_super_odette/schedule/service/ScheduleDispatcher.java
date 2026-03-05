package com.example.ms_super_odette.schedule.service;

import com.example.ms_super_odette.schedule.dto.ScheduleDTO;
import com.example.ms_super_odette.schedule.model.Schedule;
import com.example.superodette.auth.session.SessionData;
import org.springframework.aot.hint.annotation.RegisterReflectionForBinding;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ScheduleDispatcher {

    private final List<ScheduleService> services;

    public ScheduleDispatcher(List<ScheduleService> services) {
        this.services = services;
    }

    @RegisterReflectionForBinding({ ScheduleDTO.class, Schedule.class })
    public List<ScheduleDTO> getSchedule(SessionData user) {
        ScheduleService service = services.stream()
                .filter(s -> s.supports(user))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("No schedule service for user role"));

        List<Schedule> schedules = service.getSchedule(user);

        return schedules.stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    private ScheduleDTO mapToDTO(Schedule schedule) {
        return new ScheduleDTO(
                schedule.getClassGroup().getName(),
                schedule.getClassGroup().getYear(),
                schedule.getSubject().getName(),
                schedule.getTeacher().getName(),
                dayOfWeekAsString(schedule.getDayOfWeek()),
                schedule.getStartTime(),
                schedule.getEndTime()
        );
    }

    private String dayOfWeekAsString(int day) {
        return switch (day) {
            case 1 -> "Monday";
            case 2 -> "Tuesday";
            case 3 -> "Wednesday";
            case 4 -> "Thursday";
            case 5 -> "Friday";
            case 6 -> "Saturday";
            case 7 -> "Sunday";
            default -> "Unknown";
        };
    }
}