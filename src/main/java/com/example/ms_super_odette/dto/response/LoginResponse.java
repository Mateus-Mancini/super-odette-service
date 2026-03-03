package com.example.ms_super_odette.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data @AllArgsConstructor
public class LoginResponse {
    private String token;
    private String tipo;
    private String nome;
}