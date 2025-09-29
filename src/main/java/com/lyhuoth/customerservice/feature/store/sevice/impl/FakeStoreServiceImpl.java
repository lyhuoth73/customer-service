package com.lyhuoth.customerservice.feature.store.sevice.impl;

import com.lyhuoth.customerservice.clients.platziFakeStore.PlatziFakeStoreClient;
import com.lyhuoth.customerservice.clients.platziFakeStore.dto.CategoryResponseDTO;
import com.lyhuoth.customerservice.clients.platziFakeStore.dto.FileUploadResponseDTO;
import com.lyhuoth.customerservice.clients.platziFakeStore.dto.ProductCreationRequestDTO;
import com.lyhuoth.customerservice.clients.platziFakeStore.dto.ProductResponseDTO;
import com.lyhuoth.customerservice.feature.store.sevice.FakeStoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FakeStoreServiceImpl implements FakeStoreService {

    private final PlatziFakeStoreClient platziFakeStoreClient;
    @Override
    public List<CategoryResponseDTO> getCategories() {
        return platziFakeStoreClient.getCategories();
    }

    @Override
    public ProductResponseDTO getProductById(Integer id) {
        return platziFakeStoreClient.getProductById(id);
    }

    @Override
    public List<ProductResponseDTO> getProducts(Integer offset, Integer limit) {
        return platziFakeStoreClient.getProducts(offset, limit);
    }

    @Override
    public ProductResponseDTO createProduct(ProductCreationRequestDTO productCreationRequestDTO) {

        return platziFakeStoreClient.createProduct(productCreationRequestDTO);
    }

    @Override
    public FileUploadResponseDTO uploadFile(MultipartFile file) {
            return platziFakeStoreClient.uploadFile(file);
    }
}
