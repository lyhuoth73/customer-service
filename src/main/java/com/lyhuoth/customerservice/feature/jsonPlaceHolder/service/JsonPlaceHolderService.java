package com.lyhuoth.customerservice.feature.jsonPlaceHolder.service;

import com.lyhuoth.customerservice.clients.jsonPlaceHolder.dto.PostResponseDTO;

import java.util.List;

public interface JsonPlaceHolderService {
    List<PostResponseDTO> getPost();
}
