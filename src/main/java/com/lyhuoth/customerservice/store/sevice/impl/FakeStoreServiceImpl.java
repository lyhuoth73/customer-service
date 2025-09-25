package com.lyhuoth.customerservice.store.sevice.impl;

import com.lyhuoth.customerservice.clients.platziFakeStore.PlatziFakeStoreClient;
import com.lyhuoth.customerservice.clients.platziFakeStore.dto.CategoryResponseDTO;
import com.lyhuoth.customerservice.store.sevice.FakeStoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FakeStoreServiceImpl implements FakeStoreService {


    private final PlatziFakeStoreClient platziFakeStoreClient;
    @Override
    public List<CategoryResponseDTO> getCategories() {
        return platziFakeStoreClient.getCategories();
    }
}
