package com.lyhuoth.customerservice.comman.config.props;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@ConfigurationProperties(prefix = "service")
public class ServiceInfoProps {
    private String info;
    private String version;
}
