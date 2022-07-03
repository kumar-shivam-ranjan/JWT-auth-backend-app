package com.jwt.demo.mapper;

import com.jwt.demo.entities.Category;
import com.jwt.demo.payload.CategoryRequestDto;
import com.jwt.demo.payload.CategoryResponseDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
  CategoryResponseDto modelToResponseDto(Category category);

  Category dtoToModel(CategoryRequestDto categoryRequestDto);
}
