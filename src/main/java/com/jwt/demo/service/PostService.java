package com.jwt.demo.service;

import com.jwt.demo.payload.PostRequestDto;
import com.jwt.demo.payload.PostResponseDto;

import java.util.List;

public interface PostService {

  PostResponseDto createPost(PostRequestDto postRequestDto , Integer categoryId , Integer userId);

  List<PostResponseDto> getPostsByCategory(Integer categoryId);
  List<PostResponseDto> getPostsByUser(Integer userId);

  PostResponseDto updatePost(PostRequestDto postRequestDto);

  PostResponseDto getPostById(Integer id);

  void deletePost(Integer id);


}
