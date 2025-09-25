package com.lyhuoth.customerservice.aspect;

import com.lyhuoth.customerservice.config.HttpClientsRegistrar;
import org.springframework.context.annotation.Import;
import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Import(HttpClientsRegistrar.class) // This activates our scanner
public @interface EnableHttpClients {
    String[] basePackages();
}