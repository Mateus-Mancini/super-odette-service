package com.example.ms_super_odette.aspect;

import com.example.ms_super_odette.annotation.RequireRole;
import com.example.ms_super_odette.model.SessionData;
import jakarta.servlet.http.HttpServletRequest;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.server.ResponseStatusException;

import java.util.Arrays;

@Aspect
@Component
public class RequireRoleAspect {
    @Around("@annotation(requireRole)")
    public Object checkRole(ProceedingJoinPoint joinPoint, RequireRole requireRole) throws Throwable {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (attributes == null) throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Nenhuma requisição");

        HttpServletRequest request = attributes.getRequest();
        SessionData sessionData = (SessionData) request.getAttribute("SESSION_DATA");
        if (sessionData == null) throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Sessão inválida.");

        boolean hasAnyRole = Arrays.stream(requireRole.value())
                .anyMatch(role -> sessionData.getRoles().contains(role));

        if (!hasAnyRole) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Acesso negado: você não tem permissão para acessar este recurso.");
        }

        return joinPoint.proceed();
    }
}