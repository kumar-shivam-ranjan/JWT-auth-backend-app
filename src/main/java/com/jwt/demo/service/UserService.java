package com.jwt.demo.service;

import com.jwt.demo.payload.UserRequestDto;
import com.jwt.demo.payload.UserResponseDto;
import org.springframework.stereotype.Service;

import java.util.List;


public interface UserService {

  UserResponseDto createUser(UserRequestDto userRequestDto);

  UserResponseDto registerNewUser(UserRequestDto userRequestDto);

  UserResponseDto updateUser(UserRequestDto userRequestDto, Integer id);

  UserResponseDto getById(Integer id);

  List<UserResponseDto> getAllUsers();

  void deleteUser(Integer id);
}
