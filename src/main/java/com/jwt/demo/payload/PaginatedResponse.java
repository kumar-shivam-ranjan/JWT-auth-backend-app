package com.jwt.demo.payload;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class PaginatedResponse<E> {

  private List<E> content;
  private boolean isLast;
  private boolean isFirst;
  private Integer pageNumber;
  private Integer pageSize;
  private Integer totalElements;
}
