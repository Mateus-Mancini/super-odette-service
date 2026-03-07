package com.example.ms_super_odette.schedule.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;

@Getter
@Setter
public class ScheduleDTO {

    private String className;
    private Integer year;
    private String subjectName;
    private String teacherName;
    private String dayOfWeek;
    private LocalTime startTime;
    private LocalTime endTime;

    public ScheduleDTO(String className, Integer year, String subjectName,
                       String teacherName, String dayOfWeek,
                       LocalTime startTime, LocalTime endTime) {
        this.className = className;
        this.year = year;
        this.subjectName = subjectName;
        this.teacherName = teacherName;
        this.dayOfWeek = dayOfWeek;
        this.startTime = startTime;
        this.endTime = endTime;
    }
}