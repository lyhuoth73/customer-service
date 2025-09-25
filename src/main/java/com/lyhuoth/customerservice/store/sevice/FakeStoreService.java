package com.lyhuoth.customerservice.store.sevice;

import com.lyhuoth.customerservice.clients.platziFakeStore.dto.CategoryResponseDTO;

import java.util.List;

public interface FakeStoreService {
    List<CategoryResponseDTO> getCategories();
}
