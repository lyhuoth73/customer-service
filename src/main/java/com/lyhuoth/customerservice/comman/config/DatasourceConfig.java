package com.lyhuoth.customerservice.comman.config;

import com.lyhuoth.customerservice.comman.config.props.DatabaseProps;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class DatasourceConfig {

    private final DatabaseProps databaseProps;

    @Bean
    @RefreshScope
    public DataSource dataSource() {
        log.info("pgDataSource is configured : {}", databaseProps);
        return DataSourceBuilder
                .create()
                .driverClassName(databaseProps.getDriver())
                .url(databaseProps.getUrl())
                .username(databaseProps.getUsername())
                .password(databaseProps.getPassword())
                .build();
    }
}
