package com.example.ms_super_odette.service.teacher;

import com.example.ms_super_odette.dto.request.TeacherRequest;
import com.example.ms_super_odette.dto.response.TeacherResponse;
import com.example.ms_super_odette.model.Teacher;
import com.example.ms_super_odette.model.TeacherSubject;
import com.example.ms_super_odette.model.User;
import com.example.ms_super_odette.model.UserType;
import com.example.ms_super_odette.repository.SubjectRepository;
import com.example.ms_super_odette.repository.TeacherRepository;
import com.example.ms_super_odette.repository.TeacherSubjectRepository;
import com.example.ms_super_odette.repository.UserRepository;
import com.example.ms_super_odette.repository.UserTypeRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@AllArgsConstructor
public class TeacherService {

    private final TeacherRepository teacherRepo;
    private final TeacherSubjectRepository teacherSubjectRepo;
    private final SubjectRepository subjectRepo;
    private final UserRepository userRepo;
    private final UserTypeRepository userTypeRepo;
    private final PasswordEncoder passwordEncoder;
    private final TeacherMapper mapper;

    public List<TeacherResponse> getAll() {
        return teacherRepo.findAll().stream()
                .map(t -> mapper.toResponse(t, teacherSubjectRepo.findByTeacherId(t.getId())))
                .toList();
    }

    public TeacherResponse getById(Long id) {
        Teacher teacher = findOrThrow(id);
        return mapper.toResponse(teacher, teacherSubjectRepo.findByTeacherId(id));
    }

    @Transactional
    public TeacherResponse upsert(Long id, TeacherRequest request) {
        Teacher teacher;

        if (id == null) {
            if (request.getPassword() == null || request.getPassword().isBlank()) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                        "A senha é obrigatória ao cadastrar um professor.");
            }

            if (userRepo.existsByEmail(request.getEmail())) {
                throw new ResponseStatusException(HttpStatus.CONFLICT,
                        "Já existe uma conta com este e-mail.");
            }

            UserType teacherType = userTypeRepo.findByDescription("PROFESSOR")
                    .orElseThrow(() -> new IllegalStateException("User type PROFESSOR not found."));

            User user = new User();
            user.setUserType(teacherType);
            user.setName(request.getName());
            user.setEmail(request.getEmail());
            user.setPassword(passwordEncoder.encode(request.getPassword()));
            User savedUser = userRepo.save(user);

            teacher = new Teacher();
            teacher.setUser(savedUser);
            teacher.setHiredAt(request.getHiredAt());
            teacher = teacherRepo.save(teacher);

        } else {
            teacher = findOrThrow(id);

            Teacher finalTeacher = teacher;
            userRepo.findByEmail(request.getEmail()).ifPresent(existing -> {
                if (!existing.getId().equals(finalTeacher.getUser().getId())) {
                    throw new ResponseStatusException(HttpStatus.CONFLICT,
                            "Já existe uma conta com este e-mail.");
                }
            });

            teacher.getUser().setName(request.getName());
            teacher.getUser().setEmail(request.getEmail());

            if (request.getPassword() != null && !request.getPassword().isBlank()) {
                teacher.getUser().setPassword(passwordEncoder.encode(request.getPassword()));
            }

            teacher.setHiredAt(request.getHiredAt());
            teacher = teacherRepo.save(teacher);
        }

        if (request.getSubjectIds() != null) {
            replaceSubjectAssignments(teacher, request.getSubjectIds());
        }

        return mapper.toResponse(teacher, teacherSubjectRepo.findByTeacherId(teacher.getId()));
    }

    private void replaceSubjectAssignments(Teacher teacher, List<Long> subjectIds) {
        teacherSubjectRepo.deleteAllByTeacherId(teacher.getId());

        List<TeacherSubject> assignments = subjectIds.stream()
                .map(subjectId -> {
                    var subject = subjectRepo.findById(subjectId)
                            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                                    "Disciplina não encontrada: " + subjectId));
                    TeacherSubject ts = new TeacherSubject();
                    ts.setTeacher(teacher);
                    ts.setSubject(subject);
                    return ts;
                })
                .toList();

        teacherSubjectRepo.saveAll(assignments);
    }

    private Teacher findOrThrow(Long id) {
        return teacherRepo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Professor não encontrado."));
    }
}