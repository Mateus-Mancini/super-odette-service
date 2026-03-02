package com.example.superodette.schedule.service;

import com.example.superodette.auth.session.SessionData;
import com.example.superodette.schedule.model.Schedule;

import java.util.List;

public interface ScheduleService {
    boolean supports(SessionData user);
    List<Schedule> getSchedule(SessionData user);
}
