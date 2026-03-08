package com.example.ms_super_odette.service.auth;

import com.example.ms_super_odette.dto.request.LoginRequest;
import com.example.ms_super_odette.dto.response.LoginResponse;
import com.example.ms_super_odette.enums.EnrollmentStatus;
import com.example.ms_super_odette.enums.UserTypeEnum;
import com.example.ms_super_odette.model.User;
import com.example.ms_super_odette.model.SessionData;
import com.example.ms_super_odette.repository.EnrollmentRepository;
import com.example.ms_super_odette.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final EnrollmentRepository enrollmentRepository;
    private final RedisSessionService sessionService;
    private final PasswordEncoder passwordEncoder;

    public LoginResponse login(LoginRequest request) {
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED,
                        "Credenciais inválidas."));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Credenciais inválidas.");
        }

        String role = user.getUserType().getDescription();

        if (role.equals(UserTypeEnum.STUDENT.getUserType().getDescription())) {
            boolean hasPendingEnrollment = enrollmentRepository
                    .existsPendingByStudentId(user.getId());
            boolean hasActiveEnrollment = enrollmentRepository
                    .existsByStudentIdAndStatus(user.getId(), EnrollmentStatus.ENROLLED);

            if (hasPendingEnrollment && !hasActiveEnrollment) {
                throw new ResponseStatusException(HttpStatus.FORBIDDEN,
                        "Sua matrícula ainda está aguardando aprovação da secretaria.");
            }
        }

        SessionData sessionData = new SessionData(
                user.getId(),
                user.getName(),
                user.getEmail(),
                new ArrayList<>(List.of(role))
        );

        String sessionId = sessionService.createSession(sessionData, Duration.ofDays(7));

        return new LoginResponse(
                sessionId,
                user.getName(),
                user.getEmail(),
                new ArrayList<>(List.of(role))
        );
    }

    public void logout(String sessionId) {
        sessionService.deleteSession(sessionId);
    }
}