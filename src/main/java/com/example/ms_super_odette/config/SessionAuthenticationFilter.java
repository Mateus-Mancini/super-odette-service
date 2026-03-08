package com.example.ms_super_odette.config;

import com.example.ms_super_odette.model.SessionData;
import com.example.ms_super_odette.service.auth.RedisSessionService;
import com.example.ms_super_odette.service.auth.SessionCookieService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.time.Duration;
import java.util.Arrays;
import java.util.stream.Collectors;

@Component
public class SessionAuthenticationFilter extends OncePerRequestFilter {

    private final RedisSessionService sessionService;
    private final SessionCookieService cookieService;

    private static final Duration SESSION_TTL = Duration.ofDays(7);
    private static final AntPathMatcher PATH_MATCHER = new AntPathMatcher();

    public SessionAuthenticationFilter(RedisSessionService sessionService,
                                       SessionCookieService cookieService) {
        this.sessionService = sessionService;
        this.cookieService = cookieService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        String sessionId = cookieService.extractSessionId(request);

        if (sessionId != null) {
            SessionData session = sessionService.getAndExtendSession(sessionId, SESSION_TTL);
            if (session != null) {
                cookieService.addOrUpdateSessionCookie(response, sessionId);

                UsernamePasswordAuthenticationToken auth =
                        new UsernamePasswordAuthenticationToken(
                                session.getUsername(),
                                null,
                                session.getRoles().stream()
                                        .map(SimpleGrantedAuthority::new)
                                        .collect(Collectors.toList())
                        );
                SecurityContextHolder.getContext().setAuthentication(auth);
                request.setAttribute("SESSION_DATA", session);
            }
        }

        filterChain.doFilter(request, response);
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        String path = request.getServletPath();
        return Arrays.stream(SecurityConfig.PUBLIC_PATHS)
                .anyMatch(pattern -> PATH_MATCHER.match(pattern, path));
    }
}