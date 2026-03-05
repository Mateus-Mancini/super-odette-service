package com.example.superodette.config;

import com.example.superodette.auth.session.SessionData;
import org.springframework.aot.hint.MemberCategory;
import org.springframework.aot.hint.RuntimeHints;
import org.springframework.aot.hint.RuntimeHintsRegistrar;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SessionDataRuntimeHints implements RuntimeHintsRegistrar {

    @Override
    public void registerHints(RuntimeHints hints, ClassLoader classLoader) {
        hints.reflection().registerType(
                SessionData.class,
                builder -> builder.withMembers(
                        MemberCategory.INVOKE_PUBLIC_CONSTRUCTORS,
                        MemberCategory.INVOKE_PUBLIC_METHODS
                )
        );

        hints.reflection().registerType(java.util.ArrayList.class,
                builder -> builder.withMembers(MemberCategory.INVOKE_PUBLIC_CONSTRUCTORS));
    }
}