package com.example.ms_super_odette.auth.resolver;

import com.example.ms_super_odette.auth.annotation.CurrentUser;
import com.example.ms_super_odette.auth.exception.UnauthorizedException;
import com.example.ms_super_odette.auth.session.SessionData;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

@Component
public class CurrentUserArgumentResolver implements HandlerMethodArgumentResolver {

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(CurrentUser.class) &&
                parameter.getParameterType().equals(SessionData.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter,
                                  ModelAndViewContainer mavContainer,
                                  NativeWebRequest webRequest,
                                  org.springframework.web.bind.support.WebDataBinderFactory binderFactory) {

        HttpServletRequest request = ((ServletWebRequest) webRequest).getRequest();
        SessionData session = (SessionData) request.getAttribute("SESSION_DATA");

        if (session == null) {
            throw new UnauthorizedException();
        }

        return session;
    }
}