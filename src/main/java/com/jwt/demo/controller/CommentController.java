package com.jwt.demo.controller;

import com.jwt.demo.payload.ApiResponse;
import com.jwt.demo.payload.CommentRequestDto;
import com.jwt.demo.payload.CommentResponseDto;
import com.jwt.demo.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/")
public class CommentController {

  @Autowired private CommentService commentService;

  @PostMapping("post/{postId}/comments")
  public ResponseEntity<ApiResponse<CommentResponseDto>> createComment(
      @RequestBody CommentRequestDto commentRequestDto, @PathVariable Integer postId) {
    CommentResponseDto comment = this.commentService.createComment(commentRequestDto, postId);
    ApiResponse<CommentResponseDto> apiResponse =
        ApiResponse.<CommentResponseDto>builder().data(comment).errors(null).build();
    return new ResponseEntity<>(apiResponse, HttpStatus.CREATED);
  }

  @DeleteMapping("comments/{commentId}")
  public ResponseEntity<ApiResponse<String>> createComment(@PathVariable Integer commentId) {
    this.commentService.deleteComment(commentId);
    ApiResponse<String> apiResponse =
        ApiResponse.<String>builder()
            .data("Comment with id: " + commentId + " deleted successfully")
            .errors(null)
            .build();
    return new ResponseEntity<>(apiResponse, HttpStatus.OK);
  }
}
