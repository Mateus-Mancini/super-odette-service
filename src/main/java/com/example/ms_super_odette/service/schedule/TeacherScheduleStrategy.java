package com.example.ms_super_odette.service.schedule;

import com.example.ms_super_odette.model.Schedule;
import com.example.ms_super_odette.repository.ScheduleRepository;
import com.example.ms_super_odette.model.SessionData;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TeacherScheduleStrategy implements ScheduleStrategy {

    private final ScheduleRepository scheduleRepo;

    @Override
    public boolean supports(SessionData user) {
        return user.getRoles().contains("PROFESSOR");
    }

    @Override
    public List<Schedule> getSchedule(SessionData user) {
        return scheduleRepo.findByTeacherId(user.getUserId());
    }
}