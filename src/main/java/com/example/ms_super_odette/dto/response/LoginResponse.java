package com.example.ms_super_odette.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class LoginResponse {
    private String sessionId;
    private String name;
    private String email;
    private List<String> roles;
}