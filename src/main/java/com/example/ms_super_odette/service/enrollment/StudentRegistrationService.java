package com.example.ms_super_odette.service.enrollment;

import com.example.ms_super_odette.dto.request.StudentRegistrationRequest;
import com.example.ms_super_odette.dto.response.StudentRegistrationResponse;
import com.example.ms_super_odette.enums.UserTypeEnum;
import com.example.ms_super_odette.model.Enrollment;
import com.example.ms_super_odette.enums.EnrollmentStatus;
import com.example.ms_super_odette.model.Student;
import com.example.ms_super_odette.model.User;
import com.example.ms_super_odette.repository.EnrollmentRepository;
import com.example.ms_super_odette.repository.StudentRepository;
import com.example.ms_super_odette.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;

@Service
@AllArgsConstructor
public class StudentRegistrationService {

    private final UserRepository userRepo;
    private final StudentRepository studentRepo;
    private final EnrollmentRepository enrollmentRepo;
    private final EnrollmentMapper enrollmentMapper;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public StudentRegistrationResponse register(StudentRegistrationRequest request) {
        if (userRepo.existsByEmail(request.getEmail())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT,
                    "Já existe uma conta com este e-mail.");
        }

        User user = new User();
        user.setUserType(UserTypeEnum.STUDENT.getUserType());
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        User savedUser = userRepo.save(user);

        Student student = new Student();
        student.setUser(savedUser);
        student.setBirthDate(request.getBirthDate());
        Student savedStudent = studentRepo.save(student);

        Enrollment enrollment = new Enrollment();
        enrollment.setStudent(savedStudent);
        enrollment.setStatus(EnrollmentStatus.PENDING);
        enrollment.setEnrolledAt(LocalDate.now());
        Enrollment savedEnrollment = enrollmentRepo.save(enrollment);

        return new StudentRegistrationResponse(
                savedUser.getId(),
                savedUser.getName(),
                savedUser.getEmail(),
                savedStudent.getBirthDate(),
                enrollmentMapper.toResponse(savedEnrollment)
        );
    }
}