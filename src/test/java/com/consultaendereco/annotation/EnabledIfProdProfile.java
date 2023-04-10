package com.consultaendereco.annotation;

import org.springframework.test.context.junit.jupiter.EnabledIf;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ ElementType.TYPE, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@EnabledIf(expression = "#{environment.acceptsProfiles(T(org.springframework.core.env.Profiles).of('prod'))}", loadContext = true)
public @interface EnabledIfProdProfile {
}
