package com.example.ms_super_odette.service.subject;

import com.example.ms_super_odette.dto.response.SubjectResponse;
import com.example.ms_super_odette.model.Enrollment;
import com.example.ms_super_odette.model.Schedule;
import com.example.ms_super_odette.model.SessionData;
import com.example.ms_super_odette.repository.EnrollmentRepository;
import com.example.ms_super_odette.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StudentSubjectStrategy implements SubjectStrategy {

    private final EnrollmentRepository enrollmentRepo;
    private final ScheduleRepository scheduleRepo;

    @Override
    public boolean supports(SessionData user) {
        return user.getRoles().contains("ALUNO");
    }

    @Override
    public List<SubjectResponse> getSubjects(SessionData user) {
        List<Enrollment> enrollments = enrollmentRepo.findByStudentId(user.getUserId());

        return enrollments.stream()
                .flatMap(e -> scheduleRepo.findByClassGroupId(e.getClassGroup().getId()).stream())
                .collect(Collectors.groupingBy(Schedule::getSubject))
                .entrySet().stream()
                .map(entry -> {
                    var subject = entry.getKey();
                    List<String> teachers = entry.getValue().stream()
                            .map(s -> s.getTeacher().getUser().getName())
                            .distinct()
                            .toList();
                    return new SubjectResponse(subject.getId(), subject.getName(), teachers, null);
                })
                .toList();
    }
}