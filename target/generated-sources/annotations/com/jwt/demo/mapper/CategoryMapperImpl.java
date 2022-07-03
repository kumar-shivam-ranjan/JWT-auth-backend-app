package com.jwt.demo.mapper;

import com.jwt.demo.entities.Category;
import com.jwt.demo.payload.CategoryRequestDto;
import com.jwt.demo.payload.CategoryResponseDto;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-07-03T13:58:31+0530",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 18.0.1.1 (Oracle Corporation)"
)
@Component
public class CategoryMapperImpl implements CategoryMapper {

    @Override
    public CategoryResponseDto modelToResponseDto(Category category) {
        if ( category == null ) {
            return null;
        }

        CategoryResponseDto categoryResponseDto = new CategoryResponseDto();

        categoryResponseDto.setCategoryId( category.getCategoryId() );
        categoryResponseDto.setCategoryTitle( category.getCategoryTitle() );

        return categoryResponseDto;
    }

    @Override
    public Category dtoToModel(CategoryRequestDto categoryRequestDto) {
        if ( categoryRequestDto == null ) {
            return null;
        }

        Category category = new Category();

        category.setCategoryTitle( categoryRequestDto.getCategoryTitle() );
        category.setCategoryDescription( categoryRequestDto.getCategoryDescription() );

        return category;
    }
}
