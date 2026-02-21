package com.example.superodette.auth.session;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.aot.hint.annotation.RegisterReflectionForBinding;

import java.io.Serializable;
import java.util.List;

@RegisterReflectionForBinding(SessionData.class)
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

    public SessionData() {}

    public Long getUserId() {return userId; }
    public String getUsername() { return username; }
    public String getEmail() { return email; }
    public List<String> getRoles() { return roles; }
}