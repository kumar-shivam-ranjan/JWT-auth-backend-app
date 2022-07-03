package com.jwt.demo.mapper;

import com.jwt.demo.entities.Post;
import com.jwt.demo.payload.PostRequestDto;
import com.jwt.demo.payload.PostResponseDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PostMapper {
  PostResponseDto modelToDto(Post post);

  Post dtoToModel(PostRequestDto postRequestDto);
}
