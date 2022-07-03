package com.jwt.demo.payload;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CategoryResponseDto {
    @JsonProperty("category_id")
    private Integer categoryId;

    @JsonProperty("category_title")
    private String categoryTitle;
}
