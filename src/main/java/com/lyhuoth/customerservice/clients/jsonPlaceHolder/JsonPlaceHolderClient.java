package com.lyhuoth.customerservice.clients.jsonPlaceHolder;

import com.lyhuoth.customerservice.clients.jsonPlaceHolder.dto.PostResponseDTO;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;

import java.util.List;

@HttpExchange
public interface JsonPlaceHolderClient {

    @GetExchange("/posts")
    List<PostResponseDTO> getPost();
}
