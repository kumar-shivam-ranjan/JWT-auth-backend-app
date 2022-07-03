package com.jwt.demo.controller;

import com.jwt.demo.payload.ApiResponse;
import com.jwt.demo.payload.PostRequestDto;
import com.jwt.demo.payload.PostResponseDto;
import com.jwt.demo.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class PostController {

  @Autowired private PostService postService;

  @PostMapping("/user/{userId}/category/{categoryId}/posts")
  public ResponseEntity<ApiResponse<PostResponseDto>> createPost(
      @RequestBody PostRequestDto postRequestDto,
      @PathVariable Integer userId,
      @PathVariable Integer categoryId) {
    PostResponseDto postResponseDto =
        this.postService.createPost(postRequestDto, categoryId, userId);
    ApiResponse<PostResponseDto> apiResponse =
        ApiResponse.<PostResponseDto>builder().data(postResponseDto).errors(null).build();
    return new ResponseEntity<>(apiResponse, HttpStatus.CREATED);
  }
}
