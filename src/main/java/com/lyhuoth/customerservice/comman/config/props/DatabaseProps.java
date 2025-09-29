package com.lyhuoth.customerservice.comman.config.props;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties(prefix = "spring.datasource")
public class DatabaseProps {
    private String url;
    private String username;
    private String password;
    private String driver;
}
