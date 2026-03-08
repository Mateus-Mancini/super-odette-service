package com.example.ms_super_odette.controller.auth;

import com.example.ms_super_odette.annotation.CurrentUser;
import com.example.ms_super_odette.dto.request.LoginRequest;
import com.example.ms_super_odette.dto.response.LoginResponse;
import com.example.ms_super_odette.model.SessionData;
import com.example.ms_super_odette.service.auth.AuthService;
import com.example.ms_super_odette.service.auth.SessionCookieService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final SessionCookieService cookieService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(
            @Valid @RequestBody LoginRequest request,
            HttpServletResponse response) {
        LoginResponse loginResponse = authService.login(request);
        cookieService.addOrUpdateSessionCookie(response, loginResponse.getSessionId());
        return ResponseEntity.ok(loginResponse);
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logout(
            HttpServletRequest request,
            HttpServletResponse response) {
        String sessionId = cookieService.extractSessionId(request);
        authService.logout(sessionId);
        cookieService.removeSessionCookie(response);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/me")
    public ResponseEntity<Map<String, Object>> me(@CurrentUser SessionData sessionData) {
        return ResponseEntity.ok(Map.of(
                "name", sessionData.getUsername(),
                "email", sessionData.getEmail(),
                "roles", sessionData.getRoles()
        ));
    }
}