package com.lyhuoth.customerservice.store.controller;

import com.lyhuoth.customerservice.clients.platziFakeStore.dto.CategoryResponseDTO;
import com.lyhuoth.customerservice.store.sevice.FakeStoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/stores")
public class FakeStoreController {

    private final FakeStoreService fakeStoreService;

    @GetMapping("/categories")
    public List<CategoryResponseDTO> getCategories() {

        return fakeStoreService.getCategories();
    }

}
