package com.jwt.demo.payload;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@ToString
public class Error {
  private String statusCode;
  private String status;
  private String message;
}
