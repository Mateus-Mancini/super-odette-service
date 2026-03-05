package com.example.ms_super_odette.auth.session;

import java.time.Duration;

public interface SessionService {
    String createSession(SessionData sessionData, Duration ttl);
    void deleteSession(String sessionId);
    SessionData getAndExtendSession(String sessionId, Duration ttl);
}