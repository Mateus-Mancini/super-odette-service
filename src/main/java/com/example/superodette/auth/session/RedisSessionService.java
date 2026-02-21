package com.example.superodette.auth.session;

import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.types.Expiration;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.time.Duration;
import java.util.Base64;
import java.util.Objects;

@Service
public class RedisSessionService implements SessionService {

    private static final String PREFIX = "super-odette-service:session:";
    private static final SecureRandom SECURE_RANDOM = new SecureRandom();

    private final RedisTemplate<String, SessionData> redisTemplate;

    public RedisSessionService(RedisTemplate<String, SessionData> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Override
    public String createSession(SessionData sessionData, Duration ttl) {
        Objects.requireNonNull(sessionData, "sessionData must not be null");
        Objects.requireNonNull(ttl, "ttl must not be null");

        String sessionId = generateSessionId();
        String key = buildKey(sessionId);

        redisTemplate.opsForValue().set(key, sessionData, ttl);
        return sessionId;
    }

    @Override
    public void deleteSession(String sessionId) {
        if (sessionId == null || sessionId.isBlank()) return;
        redisTemplate.delete(buildKey(sessionId));
    }

    @Override
    public SessionData getAndExtendSession(String sessionId, Duration ttl) {
        if (sessionId == null || sessionId.isBlank() || ttl == null) return null;

        String key = buildKey(sessionId);
        return redisTemplate.execute((RedisCallback<SessionData>) connection -> {
            byte[] serializedKey = redisTemplate.getStringSerializer().serialize(key);
            byte[] result = connection.stringCommands().getEx(serializedKey, Expiration.from(ttl));
            if (result == null) return null;
            return (SessionData) redisTemplate.getValueSerializer().deserialize(result);
        });
    }

    private String buildKey(String sessionId) { return PREFIX + hash(sessionId); }

    private String generateSessionId() {
        byte[] randomBytes = new byte[32];
        SECURE_RANDOM.nextBytes(randomBytes);
        return Base64.getUrlEncoder().withoutPadding().encodeToString(randomBytes);
    }

    private String hash(String value) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hashed = digest.digest(value.getBytes(StandardCharsets.UTF_8));
            return Base64.getUrlEncoder().withoutPadding().encodeToString(hashed);
        } catch (Exception e) {
            throw new IllegalStateException("Failed to hash session ID", e);
        }
    }
}