package com.lyhuoth.customerservice.feature.store.sevice;

import com.lyhuoth.customerservice.clients.platziFakeStore.dto.CategoryResponseDTO;
import com.lyhuoth.customerservice.clients.platziFakeStore.dto.FileUploadResponseDTO;
import com.lyhuoth.customerservice.clients.platziFakeStore.dto.ProductCreationRequestDTO;
import com.lyhuoth.customerservice.clients.platziFakeStore.dto.ProductResponseDTO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface FakeStoreService {
    List<CategoryResponseDTO> getCategories();

    ProductResponseDTO getProductById(Integer id);

    List<ProductResponseDTO> getProducts(Integer offset, Integer limit);

    ProductResponseDTO createProduct(ProductCreationRequestDTO productCreationRequestDTO);

    FileUploadResponseDTO uploadFile(MultipartFile file);
}
