package com.example.superodette.schedule.service;

import com.example.superodette.auth.session.SessionData;
import com.example.superodette.classgroup.model.ClassGroup;
import com.example.superodette.enrollment.model.Enrollment;
import com.example.superodette.enrollment.repository.EnrollmentRepository;
import com.example.superodette.schedule.model.Schedule;
import com.example.superodette.schedule.repository.ScheduleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentScheduleService implements ScheduleService {

    private final EnrollmentRepository enrollmentRepo;
    private final ScheduleRepository scheduleRepo;

    public StudentScheduleService(EnrollmentRepository enrollmentRepo,
                                  ScheduleRepository scheduleRepo) {
        this.enrollmentRepo = enrollmentRepo;
        this.scheduleRepo = scheduleRepo;
    }

    @Override
    public boolean supports(SessionData user) {
        return user.getRoles().contains("ALUNO");
    }

    @Override
    public List<Schedule> getSchedule(SessionData user) {
        List<Enrollment> enrollments = enrollmentRepo.findByStudentId(user.getUserId());
        List<ClassGroup> classes = enrollments.stream()
                .map(Enrollment::getClassGroup)
                .toList();
        return scheduleRepo.findByClassGroupIn(classes);
    }
}