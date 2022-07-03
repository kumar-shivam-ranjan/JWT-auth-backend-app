package com.jwt.demo.payload;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CategoryRequestDto {

  @JsonProperty("category_title")
  @NotEmpty
  @Size(min = 4 , max = 20)
  private String categoryTitle;

  @NotEmpty
  @Size(min = 10)
  @JsonProperty("category_description")
  private String categoryDescription;
}
