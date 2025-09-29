package com.lyhuoth.customerservice.clients.platziFakeStore;

import com.lyhuoth.customerservice.comman.aspect.HttpServiceClient;
import com.lyhuoth.customerservice.clients.platziFakeStore.dto.CategoryResponseDTO;
import com.lyhuoth.customerservice.clients.platziFakeStore.dto.FileUploadResponseDTO;
import com.lyhuoth.customerservice.clients.platziFakeStore.dto.ProductCreationRequestDTO;
import com.lyhuoth.customerservice.clients.platziFakeStore.dto.ProductResponseDTO;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;
import org.springframework.web.service.annotation.PostExchange;

import java.util.List;

@HttpExchange
@HttpServiceClient(baseUrl = "${clients.platzifakestore.base-url}")
public interface PlatziFakeStoreClient {

    @PostExchange("/products")
    ProductResponseDTO createProduct(@RequestBody ProductCreationRequestDTO productCreationRequestDTO);

    @GetExchange("/products")
    List<ProductResponseDTO> getProducts(@RequestParam Integer offset,@RequestParam Integer limit);

    @GetExchange("/products/{id}")
    ProductResponseDTO getProductById(@PathVariable Integer id);

    @PostExchange("/files/upload")
    FileUploadResponseDTO uploadFile(@RequestPart MultipartFile file);

    @GetExchange("/categories")
    List<CategoryResponseDTO> getCategories();
}
