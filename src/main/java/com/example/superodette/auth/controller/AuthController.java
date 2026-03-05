package com.example.superodette.auth.controller;

import com.example.superodette.auth.annotation.CurrentUser;
import com.example.superodette.auth.cookie.SessionCookieService;
import com.example.superodette.auth.service.AuthService;
import com.example.superodette.auth.session.SessionData;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;
    private final SessionCookieService cookieService;

    public AuthController(AuthService authService,
                          SessionCookieService cookieService) {
        this.authService = authService;
        this.cookieService = cookieService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> body, HttpServletResponse response) {
        String email = body.get("email");
        String password = body.get("password");

        return authService.login(email, password)
                .map(sessionId -> {
                    cookieService.addOrUpdateSessionCookie(response, sessionId);
                    return ResponseEntity.ok(Map.of("sessionId", sessionId));
                })
                .orElseGet(() -> ResponseEntity.status(401)
                        .body(Map.of("error", "Invalid credentials")));
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpServletRequest request, HttpServletResponse response) {
        String sessionId = cookieService.extractSessionId(request);
        authService.logout(sessionId);
        cookieService.removeSessionCookie(response);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/me")
    public ResponseEntity<?> me(@CurrentUser SessionData sessionData) {
        return ResponseEntity.ok(Map.of(
                "name", sessionData.getUsername(),
                "email", sessionData.getEmail(),
                "roles", sessionData.getRoles()
        ));
    }
}