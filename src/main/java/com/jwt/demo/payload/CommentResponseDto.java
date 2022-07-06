package com.jwt.demo.payload;

import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CommentResponseDto {

  private Integer id;

  private String content;
}
