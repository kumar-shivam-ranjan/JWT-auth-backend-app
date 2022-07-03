package com.jwt.demo.service;

import com.jwt.demo.payload.CategoryRequestDto;
import com.jwt.demo.payload.CategoryResponseDto;

import java.util.List;

public interface CategoryService {
  CategoryResponseDto createCategory(CategoryRequestDto categoryRequestDto);

  CategoryResponseDto updateCategory(CategoryRequestDto categoryRequestDto, Integer id);

  void deleteCategory(Integer id);

  CategoryResponseDto getCategory(Integer id);

  List<CategoryResponseDto> getAllCategory();
}
