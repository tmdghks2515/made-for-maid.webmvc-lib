package io.madeformaid.webmvc.context;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(AuthContextConfiguration.class)
public @interface EnableAuthContext {
}
