package com.lyhuoth.customerservice.clients.platziFakeStore;

import com.lyhuoth.customerservice.clients.platziFakeStore.dto.CategoryResponseDTO;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;

import java.util.List;

@HttpExchange
public interface PlatziFakeStoreClient {

    @GetExchange("/categories")
    List<CategoryResponseDTO> getCategories();
}
