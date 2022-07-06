package com.jwt.demo.service.impl;

import com.jwt.demo.entities.Category;
import com.jwt.demo.entities.Post;
import com.jwt.demo.entities.User;
import com.jwt.demo.exception.ResourceNotFoundException;
import com.jwt.demo.payload.PaginatedResponse;
import com.jwt.demo.payload.PostRequestDto;
import com.jwt.demo.payload.PostResponseDto;
import com.jwt.demo.repository.CategoryRepository;
import com.jwt.demo.repository.PostRepository;
import com.jwt.demo.repository.UserRepository;
import com.jwt.demo.service.PostService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class PostServiceImpl implements PostService {

  @Autowired private PostRepository postRepository;
  @Autowired private ModelMapper modelMapper;

  @Autowired private CategoryRepository categoryRepository;
  @Autowired private UserRepository userRepository;

  @Override
  public PostResponseDto createPost(
      PostRequestDto postRequestDto, Integer categoryId, Integer userId) {

    log.info("userId:{}", userId);
    log.info("categoryId:{}", categoryId);
    User user =
        this.userRepository
            .findById(userId)
            .orElseThrow(() -> new ResourceNotFoundException("User", userId));
    Category category =
        this.categoryRepository
            .findById(categoryId)
            .orElseThrow(() -> new ResourceNotFoundException("Category", categoryId));

    Post post = this.modelMapper.map(postRequestDto, Post.class);
    post.setCategory(category);
    post.setUser(user);
    post.setAddedDate(new Date());
    post.setImage("default.png");
    this.postRepository.save(post);
    return this.modelMapper.map(post, PostResponseDto.class);
  }

  @Override
  public List<PostResponseDto> getPostsByCategory(Integer categoryId) {
    Category category =
        this.categoryRepository
            .findById(categoryId)
            .orElseThrow(() -> new ResourceNotFoundException("Category", categoryId));
    List<Post> posts = this.postRepository.findByCategory(category);
    List<PostResponseDto> postResponseDtos =
        posts.stream()
            .map((post -> this.modelMapper.map(post, PostResponseDto.class)))
            .collect(Collectors.toList());
    return postResponseDtos;
  }

  @Override
  public List<PostResponseDto> getPostsByUser(Integer userId) {
    User user =
        this.userRepository
            .findById(userId)
            .orElseThrow(() -> new ResourceNotFoundException("User", userId));
    List<Post> posts = this.postRepository.findByUser(user);
    List<PostResponseDto> postResponseDtos =
        posts.stream()
            .map((post -> this.modelMapper.map(post, PostResponseDto.class)))
            .collect(Collectors.toList());
    return postResponseDtos;
  }

  @Override
  public PaginatedResponse<PostResponseDto> getAllPosts(Integer pageSize, Integer pageNumber) {
    Pageable pageable = PageRequest.of(pageNumber, pageSize);
    Page<Post> page = this.postRepository.findAll(pageable);
    List<Post> posts = page.getContent();
    List<PostResponseDto> postResponseDtoList =
        posts.stream()
            .map((post -> this.modelMapper.map(post, PostResponseDto.class)))
            .collect(Collectors.toList());

    PaginatedResponse<PostResponseDto> paginatedResponse =
        PaginatedResponse.<PostResponseDto>builder()
            .isLast(page.isLast())
            .isFirst(page.isFirst())
            .pageNumber(page.getNumber())
            .pageSize(page.getSize())
            .content(postResponseDtoList)
            .totalElements(page.getNumberOfElements())
            .build();

    return paginatedResponse;
  }

  @Override
  public PostResponseDto updatePost(PostRequestDto postRequestDto, Integer id) {
    Post post =
        this.postRepository
            .findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Post", id));
    post.setTitle(postRequestDto.getTitle());
    post.setContent(postRequestDto.getContent());
    Post savedPost = this.postRepository.save(post);
    return this.modelMapper.map(savedPost, PostResponseDto.class);
  }

  @Override
  public PostResponseDto getPostById(Integer id) {
    Post post =
        this.postRepository
            .findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Post", id));
    log.info("Post: {}", post);
    return this.modelMapper.map(post, PostResponseDto.class);
  }

  @Override
  public void deletePost(Integer id) {
    Post post =
        this.postRepository
            .findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Post", id));
    this.postRepository.delete(post);
  }
}
