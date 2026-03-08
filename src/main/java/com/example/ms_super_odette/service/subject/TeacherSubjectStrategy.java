package com.example.ms_super_odette.service.subject;

import com.example.ms_super_odette.dto.response.SubjectResponse;
import com.example.ms_super_odette.model.Schedule;
import com.example.ms_super_odette.model.SessionData;
import com.example.ms_super_odette.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TeacherSubjectStrategy implements SubjectStrategy {

    private final ScheduleRepository scheduleRepo;

    @Override
    public boolean supports(SessionData user) {
        return user.getRoles().contains("PROFESSOR");
    }

    @Override
    public List<SubjectResponse> getSubjects(SessionData user) {
        return scheduleRepo.findByTeacherId(user.getUserId()).stream()
                .collect(Collectors.groupingBy(Schedule::getSubject))
                .entrySet().stream()
                .map(entry -> {
                    var subject = entry.getKey();
                    List<String> classes = entry.getValue().stream()
                            .map(s -> s.getClassGroup().getName() + " (" + s.getClassGroup().getYear() + ")")
                            .distinct()
                            .toList();
                    return new SubjectResponse(subject.getId(), subject.getName(), null, classes);
                })
                .toList();
    }
}