package com.jwt.demo.payload;

import lombok.*;

import java.io.Serializable;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data
public class ApiResponse<E> implements Serializable {
  private E data;
  private List<Error> errors;
}
