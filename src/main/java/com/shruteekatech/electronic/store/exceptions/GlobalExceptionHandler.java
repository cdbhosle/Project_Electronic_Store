package com.shruteekatech.electronic.store.exceptions;

import com.shruteekatech.electronic.store.dtos.ApiResponse;
import lombok.Builder;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Slf4j
@Builder
@RestControllerAdvice
public class GlobalExceptionHandler {


    //handler resource not found exceptions
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse> resourceNotFoundExceptionHandler(ResourceNotFoundException ex) {
        log.info("Exception Handler called!!");
        ApiResponse apiResponse = ApiResponse.builder().message(ex.getMessage()).success(false).build();
        return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.NOT_FOUND);

    }

    //methodAgrNotValidExc
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>>
    handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {

        List<ObjectError> allErrors = ex.getBindingResult().getAllErrors();
        Map<String,Object> response =new HashMap<>();
        allErrors.stream().forEach(objectError -> {
            String message = objectError.getDefaultMessage();
            String field = ((FieldError) objectError).getField();
            response.put(field, message);
        });

        return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(BadApiRequest.class)
    public ResponseEntity<ApiResponse> handleBadApiRequest(BadApiRequest ex) {
        log.info("bad Api request Handler called!!");
        ApiResponse apiResponse = ApiResponse.builder().message(ex.getMessage()).success(false).build();
        return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.BAD_REQUEST);

    }
}
