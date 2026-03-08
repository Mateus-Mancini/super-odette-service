package com.example.ms_super_odette.service.schedule;

import com.example.ms_super_odette.dto.response.ScheduleResponse;
import com.example.ms_super_odette.model.Schedule;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ScheduleMapper {

    public List<ScheduleResponse> toResponseList(List<Schedule> schedules) {
        return schedules.stream()
                .map(this::toResponse)
                .toList();
    }

    public ScheduleResponse toResponse(Schedule schedule) {
        return new ScheduleResponse(
                schedule.getClassGroup().getName(),
                schedule.getClassGroup().getYear(),
                schedule.getSubject().getName(),
                schedule.getTeacher().getUser().getName(),
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