package com.example.ms_super_odette.schedule.service;

import com.example.ms_super_odette.schedule.model.Schedule;
import com.example.superodette.auth.session.SessionData;

import java.util.List;

public interface ScheduleService {
    boolean supports(SessionData user);
    List<Schedule> getSchedule(SessionData user);
}
