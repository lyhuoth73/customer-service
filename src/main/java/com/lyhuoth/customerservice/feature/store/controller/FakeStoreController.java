package com.lyhuoth.customerservice.feature.store.controller;

import com.lyhuoth.customerservice.clients.platziFakeStore.dto.CategoryResponseDTO;
import com.lyhuoth.customerservice.clients.platziFakeStore.dto.FileUploadResponseDTO;
import com.lyhuoth.customerservice.clients.platziFakeStore.dto.ProductCreationRequestDTO;
import com.lyhuoth.customerservice.clients.platziFakeStore.dto.ProductResponseDTO;
import com.lyhuoth.customerservice.feature.store.sevice.FakeStoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/stores")
public class FakeStoreController {

    private final FakeStoreService fakeStoreService;

    @PostMapping("/products")
    public ProductResponseDTO createProduct(@RequestBody ProductCreationRequestDTO productCreationRequestDTO) {
        return fakeStoreService.createProduct(productCreationRequestDTO);

    }

    @PostMapping("/file/upload")
    public FileUploadResponseDTO uploadFile(@RequestPart MultipartFile file) {
        return fakeStoreService.uploadFile(file);

    }

    @GetMapping("/products")
    public List<ProductResponseDTO> getProducts(@RequestParam(required = false, defaultValue = "0") Integer offset,@RequestParam(required = false, defaultValue = "10") Integer limit) {
        return fakeStoreService.getProducts(offset, limit);
    }

    @GetMapping("/products/{id}")
    public ProductResponseDTO getProductById(@PathVariable Integer id) {
        return fakeStoreService.getProductById(id);
    }


    @GetMapping("/categories")
    public List<CategoryResponseDTO> getCategories() {
        return fakeStoreService.getCategories();
    }

}
