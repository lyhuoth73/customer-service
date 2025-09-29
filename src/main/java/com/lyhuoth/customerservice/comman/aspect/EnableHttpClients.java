package com.lyhuoth.customerservice.comman.aspect;

import com.lyhuoth.customerservice.comman.config.HttpClientsRegistrar;
import org.springframework.context.annotation.Import;
import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Import(HttpClientsRegistrar.class) // This activates our scanner
public @interface EnableHttpClients {
    String[] basePackages();
}