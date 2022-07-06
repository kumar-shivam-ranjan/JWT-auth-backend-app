package com.jwt.demo.controller;

import com.jwt.demo.payload.ApiResponse;
import com.jwt.demo.payload.PaginatedResponse;
import com.jwt.demo.payload.PostRequestDto;
import com.jwt.demo.payload.PostResponseDto;
import com.jwt.demo.service.PostService;
import org.apache.tomcat.util.http.parser.HttpParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.jwt.demo.constants.Constants.DEFAULT_PAGE_NUMBER;
import static com.jwt.demo.constants.Constants.DEFAULT_PAGE_SIZE;

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
  public ResponseEntity<ApiResponse<PaginatedResponse<PostResponseDto>>> getAllPosts(
      @RequestParam(name = "page_size", defaultValue = DEFAULT_PAGE_SIZE, required = false)
          Integer pageSize,
      @RequestParam(name = "page_no", defaultValue = DEFAULT_PAGE_NUMBER, required = false)
          Integer pageNumber) {
    PaginatedResponse<PostResponseDto> posts = this.postService.getAllPosts(pageSize, pageNumber);
    ApiResponse<PaginatedResponse<PostResponseDto>> apiResponse =
        ApiResponse.<PaginatedResponse<PostResponseDto>>builder().data(posts).errors(null).build();
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
