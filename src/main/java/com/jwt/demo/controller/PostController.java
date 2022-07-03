package com.jwt.demo.controller;

import com.jwt.demo.payload.ApiResponse;
import com.jwt.demo.payload.PostRequestDto;
import com.jwt.demo.payload.PostResponseDto;
import com.jwt.demo.service.PostService;
import org.apache.tomcat.util.http.parser.HttpParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

  @GetMapping("/category/{categoryId}/posts")
  public ResponseEntity<ApiResponse<List<PostResponseDto>>> getPostsByCategory(
      @PathVariable Integer categoryId) {
    List<PostResponseDto> postResponseDtos = this.postService.getPostsByCategory(categoryId);
    ApiResponse<List<PostResponseDto>> apiResponse =
        ApiResponse.<List<PostResponseDto>>builder().data(postResponseDtos).errors(null).build();
    return new ResponseEntity<>(apiResponse, HttpStatus.OK);
  }

  @GetMapping("/user/{userId}/posts")
  public ResponseEntity<ApiResponse<List<PostResponseDto>>> getPostsByUser(
      @PathVariable Integer userId) {
    List<PostResponseDto> postResponseDtos = this.postService.getPostsByUser(userId);
    ApiResponse<List<PostResponseDto>> apiResponse =
        ApiResponse.<List<PostResponseDto>>builder().data(postResponseDtos).errors(null).build();
    return new ResponseEntity<>(apiResponse, HttpStatus.OK);
  }

  @GetMapping("/posts")
  public ResponseEntity<ApiResponse<List<PostResponseDto>>> getAllPosts() {
    List<PostResponseDto> postResponseDtos = this.postService.getAllPosts();
    ApiResponse<List<PostResponseDto>> apiResponse =
        ApiResponse.<List<PostResponseDto>>builder().data(postResponseDtos).errors(null).build();
    return new ResponseEntity<>(apiResponse, HttpStatus.OK);
  }

  @GetMapping("/posts/{id}")
  public ResponseEntity<ApiResponse<PostResponseDto>> getPostById(@PathVariable Integer id) {
    PostResponseDto postResponseDto = this.postService.getPostById(id);
    ApiResponse<PostResponseDto> apiResponse =
        ApiResponse.<PostResponseDto>builder().data(postResponseDto).errors(null).build();
    return new ResponseEntity<>(apiResponse, HttpStatus.OK);
  }

  @DeleteMapping("/posts/{id}")
  public ResponseEntity<ApiResponse<Object>> deletePost(@PathVariable Integer id) {
    this.postService.deletePost(id);
    ApiResponse<Object> apiResponse =
        ApiResponse.<Object>builder().data("Post deleted with id:" + id).errors(null).build();
    return new ResponseEntity<>(apiResponse, HttpStatus.OK);
  }

  @PutMapping("/posts/{id}")
  public ResponseEntity<ApiResponse<PostResponseDto>> updatePost(
      @RequestBody PostRequestDto postRequestDto, @PathVariable Integer id) {
    PostResponseDto postResponseDto = this.postService.updatePost(postRequestDto, id);
    ApiResponse<PostResponseDto> apiResponse =
        ApiResponse.<PostResponseDto>builder().data(postResponseDto).errors(null).build();
    return new ResponseEntity<>(apiResponse, HttpStatus.OK);
  }
}
