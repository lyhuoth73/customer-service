package com.lyhuoth.customerservice.feature.jsonPlaceHolder.controller;

import com.lyhuoth.customerservice.clients.jsonPlaceHolder.dto.PostResponseDTO;
import com.lyhuoth.customerservice.feature.jsonPlaceHolder.service.JsonPlaceHolderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/json-place-holer")
public class JsonPlaceHolderController {

    private final JsonPlaceHolderService jsonPlaceHolderService;

    @GetMapping("/posts")
    public List<PostResponseDTO> getPost() {

        return jsonPlaceHolderService.getPost();
    }
}
