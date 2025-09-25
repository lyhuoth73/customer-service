package com.lyhuoth.customerservice.config;

import com.lyhuoth.customerservice.aspect.EnableHttpClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@Configuration
// This one line replaces all your individual @Bean methods!
@EnableHttpClients(basePackages = "com.lyhuoth.customerservice.clients")
public class AppConfig {

}
