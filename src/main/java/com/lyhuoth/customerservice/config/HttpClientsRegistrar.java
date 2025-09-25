package com.lyhuoth.customerservice.config;

import com.lyhuoth.customerservice.aspect.EnableHttpClients;
import com.lyhuoth.customerservice.aspect.HttpServiceClient;
import org.springframework.beans.factory.annotation.AnnotatedBeanDefinition;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.util.ClassUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.service.annotation.HttpExchange;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

import java.util.Map;
import java.util.Set;

public class HttpClientsRegistrar implements ImportBeanDefinitionRegistrar {


        @Override
        public void registerBeanDefinitions(AnnotationMetadata metadata, BeanDefinitionRegistry registry) {
            // 1. Scanner that finds interfaces with our custom annotation
            ClassPathScanningCandidateComponentProvider scanner = new ClassPathScanningCandidateComponentProvider(false) {
                @Override
                protected boolean isCandidateComponent(AnnotatedBeanDefinition beanDefinition) {
                    return beanDefinition.getMetadata().isInterface();
                }
            };
            scanner.addIncludeFilter(new AnnotationTypeFilter(HttpServiceClient.class));

            // 2. Get the base package to scan from @EnableHttpClients
            Map<String, Object> attrs = metadata.getAnnotationAttributes(EnableHttpClients.class.getName());
            String[] basePackages = (String[]) attrs.get("basePackages");

            // 3. Scan for interfaces and register a bean for each one
            for (String basePackage : basePackages) {
                Set<BeanDefinition> candidates = scanner.findCandidateComponents(basePackage);
                for (BeanDefinition candidate : candidates) {
                    try {
                        String interfaceName = candidate.getBeanClassName();
                        Class<?> clientInterface = ClassUtils.forName(interfaceName, null);

                        // 4. Get the baseUrl from our @HttpServiceClient annotation
                        Map<String, Object> annotationAttributes = ((AnnotatedBeanDefinition) candidate).getMetadata()
                                .getAnnotationAttributes(HttpServiceClient.class.getName());
                        String baseUrl = (String) annotationAttributes.get("baseUrl");

                        // 5. Create a WebClient specifically for this client
                        WebClient webClient = WebClient.builder().baseUrl(baseUrl).build();

                        // 6. Create the proxy factory
                        HttpServiceProxyFactory factory = HttpServiceProxyFactory
                                .builder()
                                .exchangeAdapter(WebClientAdapter.create(webClient))
                                .build();

                        // 7. Create a FactoryBean definition that will produce the client proxy
                        BeanDefinitionBuilder builder = BeanDefinitionBuilder
                                .genericBeanDefinition(HttpServiceClientFactoryBean.class);

                        builder.addConstructorArgValue(clientInterface);
                        builder.addConstructorArgValue(factory);
                        builder.setAutowireMode(AbstractBeanDefinition.AUTOWIRE_BY_TYPE);

                        // 8. Register the FactoryBean with a generated name
                        String beanName = StringUtils.uncapitalize(clientInterface.getSimpleName());
                        registry.registerBeanDefinition(beanName, builder.getBeanDefinition());

                    } catch (ClassNotFoundException e) {
                        throw new IllegalStateException("Cannot find HTTP client interface class", e);
                    }
                }
            }
        }
}