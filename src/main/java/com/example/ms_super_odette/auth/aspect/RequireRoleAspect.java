package com.example.ms_super_odette.auth.aspect;

import com.example.ms_super_odette.auth.annotation.RequireRole;
import com.example.ms_super_odette.auth.session.SessionData;
import jakarta.servlet.http.HttpServletRequest;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.server.ResponseStatusException;

@Aspect
@Component
public class RequireRoleAspect {
    @Before("@annotation(requireRole)")
    public void checkRole(JoinPoint joinPoint, RequireRole requireRole) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (attributes == null) throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "No request");

        HttpServletRequest request = attributes.getRequest();
        SessionData sessionData = (SessionData) request.getAttribute("SESSION_DATA");
        if (sessionData == null) throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid session");

        String requiredRole = requireRole.value();
        if (!sessionData.getRoles().contains(requiredRole)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Insufficient role");
        }
    }
}