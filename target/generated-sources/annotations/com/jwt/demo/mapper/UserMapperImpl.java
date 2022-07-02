package com.jwt.demo.mapper;

import com.jwt.demo.entities.User;
import com.jwt.demo.entities.User.UserBuilder;
import com.jwt.demo.payload.UserRequestDto;
import com.jwt.demo.payload.UserResponseDto;
import com.jwt.demo.payload.UserResponseDto.UserResponseDtoBuilder;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-07-02T20:58:49+0530",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 17.0.3 (Amazon.com Inc.)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public UserResponseDto modelToResponseDto(User user) {
        if ( user == null ) {
            return null;
        }

        UserResponseDtoBuilder userResponseDto = UserResponseDto.builder();

        userResponseDto.id( user.getId() );
        userResponseDto.name( user.getName() );
        userResponseDto.email( user.getEmail() );

        return userResponseDto.build();
    }

    @Override
    public User requestDtoToModel(UserRequestDto userRequestDto) {
        if ( userRequestDto == null ) {
            return null;
        }

        UserBuilder user = User.builder();

        user.name( userRequestDto.getName() );
        user.email( userRequestDto.getEmail() );
        user.password( userRequestDto.getPassword() );
        user.about( userRequestDto.getAbout() );

        return user.build();
    }
}
