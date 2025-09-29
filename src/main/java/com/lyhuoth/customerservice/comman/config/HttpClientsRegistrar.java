package com.lyhuoth.customerservice.comman.config;

import com.lyhuoth.customerservice.comman.aspect.EnableHttpClients;
import com.lyhuoth.customerservice.comman.aspect.HttpServiceClient;
import org.springframework.beans.factory.annotation.AnnotatedBeanDefinition;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.util.ClassUtils;
import org.springframework.util.StringUtils;

import java.util.Map;
import java.util.Set;

public class HttpClientsRegistrar implements ImportBeanDefinitionRegistrar, EnvironmentAware {


    private Environment environment;

    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }

    @Override
    public void registerBeanDefinitions(AnnotationMetadata metadata, BeanDefinitionRegistry registry) {
        // Scanner remains the same
        ClassPathScanningCandidateComponentProvider scanner = new ClassPathScanningCandidateComponentProvider(false) {
            @Override
            protected boolean isCandidateComponent(AnnotatedBeanDefinition beanDefinition) {
                return beanDefinition.getMetadata().isInterface();
            }
        };
        scanner.addIncludeFilter(new AnnotationTypeFilter(HttpServiceClient.class));

        Map<String, Object> attrs = metadata.getAnnotationAttributes(EnableHttpClients.class.getName());
        String[] basePackages = (String[]) attrs.get("basePackages");

        for (String basePackage : basePackages) {
            Set<BeanDefinition> candidates = scanner.findCandidateComponents(basePackage);
            for (BeanDefinition candidate : candidates) {
                try {
                    String interfaceName = candidate.getBeanClassName();
                    Class<?> clientInterface = ClassUtils.forName(interfaceName, null);

                    Map<String, Object> annotationAttributes = ((AnnotatedBeanDefinition) candidate).getMetadata()
                            .getAnnotationAttributes(HttpServiceClient.class.getName());
                    String baseUrlValue = (String) annotationAttributes.get("baseUrl");

                    // Resolve property placeholders like "${clients.placeholder.base-url}"
                    String resolvedBaseUrl = environment.resolvePlaceholders(baseUrlValue);

                    // **KEY CHANGE**: Reference the central WebClient.Builder
                    // We are now creating a definition for the FactoryBean that depends on our builder.
                    BeanDefinitionBuilder builder = BeanDefinitionBuilder
                            .genericBeanDefinition(HttpServiceClientFactoryBean.class);

                    builder.addConstructorArgValue(clientInterface);
                    builder.addConstructorArgValue(resolvedBaseUrl); // Pass the resolved URL
                    builder.addPropertyReference("webClientBuilder", "webClientBuilder"); // Inject the builder bean
                    builder.setAutowireMode(AbstractBeanDefinition.AUTOWIRE_BY_TYPE);

                    String beanName = StringUtils.uncapitalize(clientInterface.getSimpleName());
                    registry.registerBeanDefinition(beanName, builder.getBeanDefinition());

                } catch (ClassNotFoundException e) {
                    throw new IllegalStateException("Cannot find HTTP client interface class", e);
                }
            }
        }
    }
}