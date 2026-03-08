package com.example.ms_super_odette.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ScheduleResponse {
    private String className;
    private Integer year;
    private String subjectName;
    private String teacherName;
    private String dayOfWeek;
    private LocalTime startTime;
    private LocalTime endTime;
}