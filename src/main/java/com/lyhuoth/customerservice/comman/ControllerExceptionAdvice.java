package com.lyhuoth.customerservice.comman;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lyhuoth.core.SystemErrorCode;
import com.lyhuoth.core.exception.ExceptionAware;
import com.lyhuoth.web.annotation.EnableErrorHandler;
import com.lyhuoth.web.handler.AbstractExceptionHandler;
import com.lyhuoth.web.vo.ResponseMessageBuilder;
import jakarta.persistence.PersistenceException;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.RestClientResponseException;

import java.util.Map;

@EnableErrorHandler
@RestControllerAdvice
public class ControllerExceptionAdvice extends AbstractExceptionHandler {

    @Autowired
    private ObjectMapper mapper;

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleAnyException(Exception ex) {
        log(ex);
        String message = SystemErrorCode.E500.getDesc();
        String code = SystemErrorCode.E500.name();
        Throwable cause = ExceptionUtils.getRootCause(ex);
        if (cause instanceof ExceptionAware || ex instanceof ExceptionAware)
            return buildResponse(((ExceptionAware) ex).getCode(), ex.getMessage(), ex);
        else if (cause instanceof RestClientResponseException ||
                ex instanceof RestClientResponseException) {

            RestClientResponseException e = (RestClientResponseException) ex;
            ResponseMessageBuilder.ResponseMessage<Object> response =
                    ResponseMessageBuilder.ResponseMessage.fail("E333", "API error rest http client");
            response.error(ex.getMessage());
            response.data(mapper.convertValue(e.getResponseBodyAsString(), Map.class));
            return buildResponse(response, HttpStatus.valueOf(e.getRawStatusCode()));
        } else if (ex instanceof PersistenceException) {
            message = "API ERROR";
            code = "E555";
        }
        return buildResponse(code, message, ex);
    }
}
