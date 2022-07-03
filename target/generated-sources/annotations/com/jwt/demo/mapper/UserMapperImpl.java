package com.jwt.demo.mapper;

import com.jwt.demo.entities.User;
import com.jwt.demo.payload.UserRequestDto;
import com.jwt.demo.payload.UserResponseDto;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-07-03T13:58:31+0530",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 18.0.1.1 (Oracle Corporation)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public UserResponseDto modelToResponseDto(User user) {
        if ( user == null ) {
            return null;
        }

        UserResponseDto userResponseDto = new UserResponseDto();

        userResponseDto.setId( user.getId() );
        userResponseDto.setName( user.getName() );
        userResponseDto.setEmail( user.getEmail() );

        return userResponseDto;
    }

    @Override
    public User requestDtoToModel(UserRequestDto userRequestDto) {
        if ( userRequestDto == null ) {
            return null;
        }

        User user = new User();

        user.setName( userRequestDto.getName() );
        user.setEmail( userRequestDto.getEmail() );
        user.setPassword( userRequestDto.getPassword() );
        user.setAbout( userRequestDto.getAbout() );

        return user;
    }
}
