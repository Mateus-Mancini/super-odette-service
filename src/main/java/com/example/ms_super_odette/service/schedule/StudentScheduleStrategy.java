package com.example.ms_super_odette.service.schedule;

import com.example.ms_super_odette.model.Schedule;
import com.example.ms_super_odette.repository.ScheduleRepository;
import com.example.ms_super_odette.model.SessionData;
import com.example.ms_super_odette.model.ClassGroup;
import com.example.ms_super_odette.model.Enrollment;
import com.example.ms_super_odette.repository.EnrollmentRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentScheduleStrategy implements ScheduleStrategy {

    private final EnrollmentRepository enrollmentRepo;
    private final ScheduleRepository scheduleRepo;

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