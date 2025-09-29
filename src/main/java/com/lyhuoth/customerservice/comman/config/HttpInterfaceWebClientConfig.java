//package com.lyhuoth.customerservice.config;
//
//import com.lyhuoth.customerservice.client.platziFakeStore.PlatziFakeStoreClient;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.reactive.function.client.WebClient;
//import org.springframework.web.reactive.function.client.support.WebClientAdapter;
//import org.springframework.web.service.invoker.HttpServiceProxyFactory;
//
//@Configuration
//public class HttpInterfaceWebClientConfig {

////    @Bean
////    public PlatziFakeStoreClient platziFakeStoreClient() {
////        // Step 1 => Create web client object
////        WebClient webClient = WebClient.builder()
////                .baseUrl("https://api.escuelajs.co/api/v1")
////                .build();
////
////        // Step 2 => Create http proxy factory
////        HttpServiceProxyFactory factory = HttpServiceProxyFactory.builder()
////                .exchangeAdapter(WebClientAdapter.create(webClient))
////                .build();
////        return factory.createClient(PlatziFakeStoreClient.class);
////
////    }
//}
