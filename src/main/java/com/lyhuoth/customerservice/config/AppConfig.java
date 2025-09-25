package com.lyhuoth.customerservice.config;

import com.lyhuoth.customerservice.aspect.EnableHttpClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@Configuration
// This one line replaces all your individual @Bean methods!
@EnableHttpClients(basePackages = "com.lyhuoth.customerservice.client")
public class AppConfig {

    // You still need one generic WebClient...
    @Bean
    public WebClient genericWebClient() {
        return WebClient.builder().build();
    }

    // ...and one master HttpServiceProxyFactory for the scanner to use.
    @Bean
    public HttpServiceProxyFactory httpServiceProxyFactory(WebClient genericWebClient) {
        return HttpServiceProxyFactory
                .builder()
                .exchangeAdapter(WebClientAdapter.create(genericWebClient))
                .build();
    }
}
////    @Bean
////    public PlatziFakeStoreClient platziFakeStoreClient() {
////        // Step 1 => Create web client object
////        WebClient webClient = WebClient.builder()
////                .baseUrl("https://api.escuelajs.co/api/v1")
////                .build();
////
////        // Step 2 => Create http proxy factory
////        HttpServiceProxyFactory factory = HttpServiceProxyFactory.builder()
////                .exchangeAdapter(WebClientAdapter.create(webClient))
////                .build();
////        return factory.createClient(PlatziFakeStoreClient.class);
////
////    }