package com.example.ms_super_odette.schedule.service;

import com.example.ms_super_odette.schedule.model.Schedule;
import com.example.ms_super_odette.schedule.repository.ScheduleRepository;
import com.example.superodette.auth.session.SessionData;
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