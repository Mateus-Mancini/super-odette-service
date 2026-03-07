package com.example.ms_super_odette.auth.service;

import com.example.ms_super_odette.auth.model.User;
import com.example.ms_super_odette.auth.repository.UserRepository;
import com.example.ms_super_odette.auth.session.RedisSessionService;
import com.example.ms_super_odette.auth.session.SessionData;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Optional;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final RedisSessionService sessionService;
    private final PasswordEncoder passwordEncoder;

    public AuthService(UserRepository userRepository,
                       RedisSessionService sessionService,
                       PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.sessionService = sessionService;
        this.passwordEncoder = passwordEncoder;
    }

    public Optional<String> login(String email, String password) {
        Optional<User> userOpt = userRepository.findByEmail(email);
        if (userOpt.isEmpty()) return Optional.empty();

        User user = userOpt.get();

        if (!passwordEncoder.matches(password, user.getPassword())) return Optional.empty();

        String role = user.getUserType().getDescription();

        SessionData sessionData = new SessionData(user.getId(), user.getName(), user.getEmail(), new ArrayList<>(java.util.List.of(role)));
        String sessionId = sessionService.createSession(sessionData, Duration.ofDays(7));

        return Optional.of(sessionId);
    }

    public void logout(String sessionId) {
        sessionService.deleteSession(sessionId);
    }
}