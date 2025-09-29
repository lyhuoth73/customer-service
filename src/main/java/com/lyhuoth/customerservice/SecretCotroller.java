package com.lyhuoth.customerservice;

import com.lyhuoth.customerservice.comman.config.props.DatabaseProps;
import com.lyhuoth.customerservice.comman.config.props.ServiceInfoProps;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequiredArgsConstructor
public class SecretCotroller {

    private final ServiceInfoProps serviceInfoProps;
    private final DatabaseProps databaseProps;

    @GetMapping("/secrets")
    public Map<String, String> secret() {
       return Map.of("info : ",serviceInfoProps.getInfo(), "dbUrl :", databaseProps.getUrl(), "version :", serviceInfoProps.getVersion());
    }
}
