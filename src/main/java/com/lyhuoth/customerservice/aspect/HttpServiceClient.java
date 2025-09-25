package com.lyhuoth.customerservice.aspect;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.springframework.stereotype.Component;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Component
public @interface HttpServiceClient {
    /**
     * The base URL of the HTTP service. This can be a property placeholder.
     * e.g., "${clients.placeholder.base-url}"
     */
    String baseUrl();
}