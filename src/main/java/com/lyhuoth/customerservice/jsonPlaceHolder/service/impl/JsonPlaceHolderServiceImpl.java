package com.lyhuoth.customerservice.jsonPlaceHolder.service.impl;

import com.lyhuoth.customerservice.clients.jsonPlaceHolder.JsonPlaceHolderClient;
import com.lyhuoth.customerservice.clients.jsonPlaceHolder.dto.PostResponseDTO;
import com.lyhuoth.customerservice.jsonPlaceHolder.service.JsonPlaceHolderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class JsonPlaceHolderServiceImpl implements JsonPlaceHolderService {

    private final JsonPlaceHolderClient jsonPlaceHolderClient;
    @Override
    public List<PostResponseDTO> getPost() {

        return jsonPlaceHolderClient.getPost();
    }
}
