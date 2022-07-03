package com.jwt.demo.service;

import com.jwt.demo.payload.PostRequestDto;
import com.jwt.demo.payload.PostResponseDto;

import java.util.List;

public interface PostService {

  PostResponseDto createPost(PostRequestDto postRequestDto , Integer categoryId , Integer userId);

  PostResponseDto updatePost(PostRequestDto postRequestDto);

  PostResponseDto getPostById(Integer id);

  void deletePost(Integer id);

  List<PostResponseDto> getAllPosts();
}
