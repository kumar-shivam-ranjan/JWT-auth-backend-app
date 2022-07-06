package com.jwt.demo.service.impl;

import com.jwt.demo.entities.Comment;
import com.jwt.demo.entities.Post;
import com.jwt.demo.exception.ResourceNotFoundException;
import com.jwt.demo.payload.CommentRequestDto;
import com.jwt.demo.payload.CommentResponseDto;
import com.jwt.demo.repository.CommentRepository;
import com.jwt.demo.repository.PostRepository;
import com.jwt.demo.service.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {
  @Autowired private CommentRepository commentRepository;
  @Autowired private PostRepository postRepository;
  @Autowired private ModelMapper modelMapper;

  @Override
  public CommentResponseDto createComment(CommentRequestDto commentRequestDto, Integer postId) {

    Post post =
        this.postRepository
            .findById(postId)
            .orElseThrow(() -> new ResourceNotFoundException("Post", postId));
    Comment comment = this.modelMapper.map(commentRequestDto, Comment.class);
    comment.setPost(post);
    Comment savedComment = this.commentRepository.save(comment);
    return this.modelMapper.map(savedComment, CommentResponseDto.class);
  }

  @Override
  public void deleteComment(Integer id) {
    Comment comment =
        this.commentRepository
            .findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Comment", id));
    this.commentRepository.delete(comment);
  }
}
