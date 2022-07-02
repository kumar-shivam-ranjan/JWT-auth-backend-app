package com.jwt.demo.exception;

import com.jwt.demo.constants.API;
import com.jwt.demo.constants.Status;
import com.jwt.demo.payload.ApiResponse;
import com.jwt.demo.payload.Error;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestControllerAdvice
public class ApiError {

  @ExceptionHandler(ResourceNotFoundException.class)
  public ResponseEntity<ApiResponse<Object>> handleResourceNotFoundException(
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
                        .status(Status.NOT_FOUND)
                        .build()))
            .build();
    return new ResponseEntity<>(apiResponse, HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<ApiResponse<Object>> handleMethodArgumentNotValidException(
      MethodArgumentNotValidException methodArgumentNotValidException) {

    List<ObjectError> errors = methodArgumentNotValidException.getBindingResult().getAllErrors();
    List<Error> errorList = new ArrayList<>();
    errors.forEach(
        error -> {
          String field = ((FieldError) error).getField();
          String message = error.getDefaultMessage();
          errorList.add(
              Error.builder()
                  .statusCode(API.BAD_REQUEST)
                  .status(Status.BAD_REQUEST)
                  .message("Error occurred in field : " + field + ", Message:" + message)
                  .build());
        });
    ApiResponse<Object> apiResponse = ApiResponse.builder().data(null).errors(errorList).build();
    return new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);
  }
}
