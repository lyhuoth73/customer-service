package com.lyhuoth.customerservice.clients.platziFakeStore.dto;

import lombok.Data;

@Data
public class CategoryResponseDTO {

    private Integer id;
    private String name;
    private String slug;
    private String image;
    private String creationAt;
    private String updatedAt;
}
