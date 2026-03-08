package com.example.ms_super_odette.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.aot.hint.annotation.RegisterReflectionForBinding;

import java.io.Serializable;
import java.util.List;

@Getter
@RegisterReflectionForBinding(SessionData.class)
@NoArgsConstructor
public class SessionData implements Serializable {
    private Long userId;
    private String username;
    private String email;
    private List<String> roles;

    @JsonCreator
    public SessionData(
            @JsonProperty("userId") Long userId,
            @JsonProperty("username") String username,
            @JsonProperty("email") String email,
            @JsonProperty("roles") List<String> roles
    ) {
        this.userId = userId;
        this.username = username;
        this.email = email;
        this.roles = roles;
    }
}