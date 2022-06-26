package com.jwt.demo.mapper;

import com.jwt.demo.entities.User;
import com.jwt.demo.payload.UserRequestDto;
import com.jwt.demo.payload.UserResponseDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserResponseDto modelToResponseDto(User user);
    User requestDtoToModel(UserRequestDto userRequestDto);
}
