package com.example.superodette.schedule.service;

import com.example.superodette.auth.session.SessionData;
import com.example.superodette.schedule.model.Schedule;
import com.example.superodette.schedule.repository.ScheduleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherScheduleService implements ScheduleService {

    private final ScheduleRepository scheduleRepo;

    public TeacherScheduleService(ScheduleRepository scheduleRepo) {
        this.scheduleRepo = scheduleRepo;
    }

    @Override
    public boolean supports(SessionData user) {
        return user.getRoles().contains("PROFESSOR");
    }

    @Override
    public List<Schedule> getSchedule(SessionData user) {
        return scheduleRepo.findByTeacherId(user.getUserId());
    }
}