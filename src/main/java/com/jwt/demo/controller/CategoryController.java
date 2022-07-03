package com.jwt.demo.controller;


import com.jwt.demo.payload.ApiResponse;
import com.jwt.demo.payload.CategoryRequestDto;
import com.jwt.demo.payload.CategoryResponseDto;
import com.jwt.demo.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
@Slf4j
public class CategoryController {

  @Autowired private CategoryService categoryService;


  @GetMapping("categories")
  public ResponseEntity<ApiResponse<List<CategoryResponseDto>>> getAllCategories() {
    List<CategoryResponseDto> categories = this.categoryService.getAllCategory();
    ApiResponse<List<CategoryResponseDto>> apiResponse =
        ApiResponse.<List<CategoryResponseDto>>builder().data(categories).errors(null).build();
    return new ResponseEntity<>(apiResponse, HttpStatus.OK);
  }

  @PostMapping("categories")
  public ResponseEntity<ApiResponse<CategoryResponseDto>> createCategory(
      @Valid @RequestBody CategoryRequestDto categoryRequestDto) {
    CategoryResponseDto categoryResponseDto =
        this.categoryService.createCategory(categoryRequestDto);
    ApiResponse<CategoryResponseDto> apiResponse =
        ApiResponse.<CategoryResponseDto>builder().data(categoryResponseDto).errors(null).build();
    return new ResponseEntity<>(apiResponse, HttpStatus.CREATED);
  }

  @GetMapping("categories/{id}")
  public ResponseEntity<ApiResponse<CategoryResponseDto>> getCategoryById(
      @PathVariable Integer id) {

    CategoryResponseDto category = this.categoryService.getCategory(id);
    ApiResponse<CategoryResponseDto> apiResponse =
        ApiResponse.<CategoryResponseDto>builder().data(category).errors(null).build();
    return new ResponseEntity<>(apiResponse, HttpStatus.OK);
  }

  @DeleteMapping("categories/{id}")
  public ResponseEntity<ApiResponse<Object>> deleteCategory(@PathVariable Integer id) {
    this.categoryService.deleteCategory(id);
    ApiResponse<Object> apiResponse =
        ApiResponse.builder()
            .data("Category with id " + id + " deleted successfully")
            .errors(null)
            .build();
    return new ResponseEntity<>(apiResponse, HttpStatus.OK);
  }
}
