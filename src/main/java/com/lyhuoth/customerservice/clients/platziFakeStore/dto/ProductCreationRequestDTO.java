package com.lyhuoth.customerservice.clients.platziFakeStore.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
public class ProductCreationRequestDTO {

    private String title;
    private String slug;
    private BigDecimal price;
    private String description;
    private Integer categoryId;
    private List<String> images;
}
