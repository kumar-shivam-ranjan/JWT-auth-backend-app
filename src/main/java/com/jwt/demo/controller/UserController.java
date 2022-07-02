package com.jwt.demo.controller;

import com.jwt.demo.constants.API;
import com.jwt.demo.constants.Status;
import com.jwt.demo.payload.ApiResponse;
import com.jwt.demo.payload.Error;
import com.jwt.demo.payload.UserRequestDto;
import com.jwt.demo.payload.UserResponseDto;
import com.jwt.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/v1/")
public class UserController {

  @Autowired private UserService userService;

  @PostMapping("users")
  public ResponseEntity<ApiResponse<UserResponseDto>> createUser(
     @Valid @RequestBody UserRequestDto userRequestDto) {

    try {
      UserResponseDto userResponseDto = this.userService.createUser(userRequestDto);

      ApiResponse<UserResponseDto> apiResponse =
          ApiResponse.<UserResponseDto>builder().data(userResponseDto).errors(null).build();
      return new ResponseEntity<>(apiResponse, HttpStatus.CREATED);
    } catch (Exception e) {
      ApiResponse<UserResponseDto> apiResponse =
          ApiResponse.<UserResponseDto>builder()
              .data(null)
              .errors(
                  Arrays.asList(
                      Error.builder()
                          .message("Something Went Wrong")
                          .status(Status.INTERNAL_SERVER_ERROR)
                          .statusCode(API.INTERNAL_SEVER_ERROR)
                          .build()))
              .build();

      return new ResponseEntity<>(apiResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @PutMapping("users/{id}")
  public ResponseEntity<ApiResponse<UserResponseDto>> updateUser(
      @RequestBody UserRequestDto userRequestDto, @PathVariable("id") Integer id) {

    UserResponseDto userResponseDto = this.userService.updateUser(userRequestDto, id);
    ApiResponse<UserResponseDto> apiResponse =
        ApiResponse.<UserResponseDto>builder().data(userResponseDto).errors(null).build();
    return new ResponseEntity<>(apiResponse, HttpStatus.OK);
  }

  @DeleteMapping("users/{id}")
  public ResponseEntity<ApiResponse<String>> deleteUser(@PathVariable("id") Integer id) {

    this.userService.deleteUser(id);
    ApiResponse<String> apiResponse =
        ApiResponse.<String>builder().data("User deleted successfully").errors(null).build();
    return new ResponseEntity<>(apiResponse, HttpStatus.OK);
  }

  @GetMapping("users")
  public ResponseEntity<ApiResponse<List<UserResponseDto>>> getAllUsers() {
    List<UserResponseDto> users = this.userService.getAllUsers();
    ApiResponse<List<UserResponseDto>> apiResponse =
        ApiResponse.<List<UserResponseDto>>builder().data(users).errors(null).build();
    return new ResponseEntity<>(apiResponse, HttpStatus.OK);
  }

  @GetMapping("users/{id}")
  public ResponseEntity<ApiResponse<UserResponseDto>> getUserById(@PathVariable Integer id) {

    UserResponseDto user = this.userService.getById(id);
    ApiResponse<UserResponseDto> apiResponse =
        ApiResponse.<UserResponseDto>builder().data(user).errors(null).build();
    return new ResponseEntity<>(apiResponse, HttpStatus.OK);
  }
}
