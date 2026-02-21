package com.example.superodette.config;

import com.example.superodette.auth.session.SessionData;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportRuntimeHints;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJacksonJsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import tools.jackson.databind.jsontype.BasicPolymorphicTypeValidator;

@Configuration
@ImportRuntimeHints(SessionDataRuntimeHints.class)
public class RedisConfig {

    @Bean
    public RedisTemplate<String, SessionData> redisTemplate(RedisConnectionFactory connectionFactory) {
        RedisTemplate<String, SessionData> template = new RedisTemplate<>();
        template.setConnectionFactory(connectionFactory);

        template.setKeySerializer(new StringRedisSerializer());
        template.setHashKeySerializer(new StringRedisSerializer());

        // Build serializer with default typing
        GenericJacksonJsonRedisSerializer serializer =
                GenericJacksonJsonRedisSerializer
                        .builder()
                        .enableDefaultTyping(
                                BasicPolymorphicTypeValidator.builder()
                                        .allowIfSubType(SessionData.class)
                                        .allowIfSubType("java.util.")
                                        .build()
                        )
                        .build();

        template.setValueSerializer(serializer);
        template.setHashValueSerializer(serializer);

        template.afterPropertiesSet();
        return template;
    }
}