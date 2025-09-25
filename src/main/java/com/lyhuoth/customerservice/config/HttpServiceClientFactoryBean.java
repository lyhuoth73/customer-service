package com.lyhuoth.customerservice.config;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

public class HttpServiceClientFactoryBean<T> implements FactoryBean<T> {

    private final Class<T> clientInterface;
    private final String baseUrl;
    private WebClient.Builder webClientBuilder;

    public HttpServiceClientFactoryBean(Class<T> clientInterface, String baseUrl) {
        this.clientInterface = clientInterface;
        this.baseUrl = baseUrl;
    }

    // Spring will call this method to inject the shared builder
    public void setWebClientBuilder(WebClient.Builder webClientBuilder) {
        this.webClientBuilder = webClientBuilder;
    }

    @Override
    public T getObject() throws Exception {
        // 1. Clone the shared builder to avoid modifying the original bean
        WebClient webClient = this.webClientBuilder.clone()
                .baseUrl(this.baseUrl) // 2. Apply the specific base URL
                .build();

        // 3. Create the proxy factory using the newly built WebClient
        HttpServiceProxyFactory factory = HttpServiceProxyFactory
                .builder()
                .exchangeAdapter(WebClientAdapter.create(webClient))
                .build();

        return factory.createClient(this.clientInterface);
    }

    @Override
    public Class<?> getObjectType() {
        return this.clientInterface;
    }
}