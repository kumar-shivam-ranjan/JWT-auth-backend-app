package com.jwt.demo.payload;

import com.jwt.demo.entities.Category;
import com.jwt.demo.entities.User;
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
}
