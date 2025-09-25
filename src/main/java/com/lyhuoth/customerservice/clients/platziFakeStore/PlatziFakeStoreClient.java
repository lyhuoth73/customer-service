package com.lyhuoth.customerservice.clients.platziFakeStore;

import com.lyhuoth.customerservice.aspect.HttpServiceClient;
import com.lyhuoth.customerservice.clients.platziFakeStore.dto.CategoryResponseDTO;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;

import java.util.List;

@HttpExchange
@HttpServiceClient(baseUrl = "https://api.escuelajs.co/api/v1")
public interface PlatziFakeStoreClient {

    @GetExchange("/categories")
    List<CategoryResponseDTO> getCategories();
}
