package com.lyhuoth.customerservice.config;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

public class HttpServiceClientFactoryBean<T> implements FactoryBean<T> {

    private final Class<T> clientInterface;
    private final HttpServiceProxyFactory factory;

    public HttpServiceClientFactoryBean(Class<T> clientInterface, HttpServiceProxyFactory factory) {
        this.clientInterface = clientInterface;
        this.factory = factory;
    }

    @Override
    public T getObject() throws Exception {
        // The core logic: use the factory to create the client proxy
        return factory.createClient(this.clientInterface);
    }

    @Override
    public Class<?> getObjectType() {
        return this.clientInterface;
    }
}