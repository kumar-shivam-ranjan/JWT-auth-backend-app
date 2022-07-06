package com.jwt.demo.payload;

import lombok.*;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PostResponseDto {
  private Integer postId;
  private String title;
  private String content;
  private Date addedDate;
  private CategoryResponseDto category;
  private UserResponseDto user;
  private Set<CommentResponseDto> comments=new HashSet<>();
}
