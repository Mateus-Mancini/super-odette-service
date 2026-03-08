package com.example.ms_super_odette.service.schedule;

import com.example.ms_super_odette.model.SessionData;
import com.example.ms_super_odette.model.Schedule;

import java.util.List;

public interface ScheduleStrategy {
    boolean supports(SessionData user);
    List<Schedule> getSchedule(SessionData user);
}
