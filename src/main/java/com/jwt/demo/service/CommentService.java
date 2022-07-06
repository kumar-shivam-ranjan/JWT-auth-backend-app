package com.jwt.demo.service;

import com.jwt.demo.payload.CommentRequestDto;
import com.jwt.demo.payload.CommentResponseDto;

public interface CommentService {
  CommentResponseDto createComment(CommentRequestDto commentRequestDto , Integer postId);

  void deleteComment(Integer id);
}
