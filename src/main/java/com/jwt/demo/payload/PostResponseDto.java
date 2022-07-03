package com.jwt.demo.payload;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class PostResponseDto {
  private Integer postId;
  private String title;
  private String content;
  private Date addedDate;
  private CategoryResponseDto category;
  private UserResponseDto user;
}
