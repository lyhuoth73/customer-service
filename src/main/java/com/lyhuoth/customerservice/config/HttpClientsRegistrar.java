package com.lyhuoth.customerservice.config;

import com.lyhuoth.customerservice.aspect.EnableHttpClients;
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
import org.springframework.web.service.annotation.HttpExchange;

import java.util.Map;
import java.util.Set;

public class HttpClientsRegistrar implements ImportBeanDefinitionRegistrar {

    @Override
    public void registerBeanDefinitions(AnnotationMetadata metadata, BeanDefinitionRegistry registry) {
        // 1. Set up a scanner that looks for interfaces with @HttpExchange
        ClassPathScanningCandidateComponentProvider scanner =
                new ClassPathScanningCandidateComponentProvider(false) {
                    @Override
                    protected boolean isCandidateComponent(AnnotatedBeanDefinition beanDefinition) {
                        return beanDefinition.getMetadata().isInterface();
                    }
                };
        scanner.addIncludeFilter(new AnnotationTypeFilter(HttpExchange.class));

        // 2. Get the base package to scan from our @EnableHttpClients annotation
        Map<String, Object> attrs = metadata.getAnnotationAttributes(EnableHttpClients.class.getName());
        String[] basePackages = (String[]) attrs.get("basePackages");

        // 3. Scan for the interfaces and register a bean for each one
        for (String basePackage : basePackages) {
            Set<BeanDefinition> candidates = scanner.findCandidateComponents(basePackage);
            for (BeanDefinition candidate : candidates) {
                try {
                    String interfaceName = candidate.getBeanClassName();
                    Class<?> clientInterface = ClassUtils.forName(interfaceName, null);

                    // 4. Create a definition for our FactoryBean
                    BeanDefinitionBuilder builder = BeanDefinitionBuilder.genericBeanDefinition(HttpServiceClientFactoryBean.class);

                    // 5. Pass the interface class and the master factory to the FactoryBean's constructor
                    builder.addConstructorArgValue(clientInterface);
                    builder.addConstructorArgReference("httpServiceProxyFactory"); // Assumes this bean exists
                    builder.setAutowireMode(AbstractBeanDefinition.AUTOWIRE_BY_TYPE);

                    // 6. Register the FactoryBean with a generated name (e.g., "dynamicCategoryClient")
                    String beanName = StringUtils.uncapitalize(clientInterface.getSimpleName());
                    registry.registerBeanDefinition(beanName, builder.getBeanDefinition());

                } catch (ClassNotFoundException e) {
                    throw new IllegalStateException("Cannot find HTTP client interface class", e);
                }
            }
        }
    }
}