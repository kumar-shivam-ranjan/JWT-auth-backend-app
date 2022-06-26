package com.jwt.demo.exception;

import com.jwt.demo.constants.API;
import com.jwt.demo.payload.ApiResponse;
import com.jwt.demo.payload.Error;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Arrays;

@RestControllerAdvice
public class ApiError {

  @ExceptionHandler(ResourceNotFoundException.class)
  public ResponseEntity<ApiResponse<Object>> resourceNotFound(
      ResourceNotFoundException resourceNotFoundException) {
    String message = resourceNotFoundException.getMessage();
    ApiResponse<Object> apiResponse =
        ApiResponse.builder()
            .data(null)
            .errors(
                Arrays.asList(
                    Error.builder()
                        .message(message)
                        .statusCode(API.NOT_FOUND)
                        .status("NOT FOUND")
                        .build()))
            .build();
    return new ResponseEntity<>(apiResponse, HttpStatus.NOT_FOUND);
  }
}
