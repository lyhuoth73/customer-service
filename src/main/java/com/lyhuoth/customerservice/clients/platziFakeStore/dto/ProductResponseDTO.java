package com.lyhuoth.customerservice.clients.platziFakeStore.dto;

import lombok.Data;

import java.util.List;

@Data
public class ProductResponseDTO {

    private Long id;
    private String title;
    private String slug;
    private String price;
    private String description;
    private CategoryResponseDTO category;
    private List<String> images;
}
