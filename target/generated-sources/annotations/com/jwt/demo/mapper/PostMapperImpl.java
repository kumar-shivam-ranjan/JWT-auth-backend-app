package com.jwt.demo.mapper;

import com.jwt.demo.entities.Post;
import com.jwt.demo.entities.Post.PostBuilder;
import com.jwt.demo.payload.PostRequestDto;
import com.jwt.demo.payload.PostResponseDto;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-07-03T19:47:42+0530",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 18.0.1.1 (Oracle Corporation)"
)
@Component
public class PostMapperImpl implements PostMapper {

    @Override
    public PostResponseDto modelToDto(Post post) {
        if ( post == null ) {
            return null;
        }

        PostResponseDto postResponseDto = new PostResponseDto();

        postResponseDto.setPostId( post.getPostId() );
        postResponseDto.setTitle( post.getTitle() );
        postResponseDto.setContent( post.getContent() );
        postResponseDto.setAddedDate( post.getAddedDate() );

        return postResponseDto;
    }

    @Override
    public Post dtoToModel(PostRequestDto postRequestDto) {
        if ( postRequestDto == null ) {
            return null;
        }

        PostBuilder post = Post.builder();

        post.title( postRequestDto.getTitle() );
        post.content( postRequestDto.getContent() );

        return post.build();
    }
}
