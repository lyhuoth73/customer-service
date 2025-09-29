package com.lyhuoth.customerservice;

import com.lyhuoth.customerservice.comman.config.props.DatabaseProps;
import com.lyhuoth.customerservice.comman.config.props.ServiceInfoProps;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableConfigurationProperties(value = {
        ServiceInfoProps.class,
        DatabaseProps.class
})
@EnableDiscoveryClient
@SpringBootApplication
public class CustomerServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CustomerServiceApplication.class, args);
    }

}
