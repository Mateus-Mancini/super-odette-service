package com.example.ms_super_odette;

import org.hibernate.internal.CoreMessageLogger;
import org.hibernate.internal.EntityManagerMessageLogger;
import org.springframework.aot.hint.MemberCategory;
import org.springframework.aot.hint.RuntimeHints;
import org.springframework.aot.hint.RuntimeHintsRegistrar;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportRuntimeHints;

@Configuration
@ImportRuntimeHints(NativeHints.Hints.class)
public class NativeHints {

    static class Hints implements RuntimeHintsRegistrar {

        @Override
        public void registerHints(RuntimeHints hints, ClassLoader classLoader) {

            hints.proxies().registerJdkProxy(EntityManagerMessageLogger.class);
            hints.proxies().registerJdkProxy(CoreMessageLogger.class);

            hints.reflection().registerType(
                    EntityManagerMessageLogger.class,
                    MemberCategory.INVOKE_PUBLIC_METHODS
            );

            hints.reflection().registerType(
                    CoreMessageLogger.class,
                    MemberCategory.INVOKE_PUBLIC_METHODS
            );
        }
    }
}