package com.jwt.demo.service.impl;

import com.jwt.demo.entities.User;
import com.jwt.demo.exception.ResourceNotFoundException;
import com.jwt.demo.payload.UserRequestDto;
import com.jwt.demo.payload.UserResponseDto;
import com.jwt.demo.repository.UserRepository;
import com.jwt.demo.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

  @Autowired private UserRepository userRepository;
  @Autowired private ModelMapper modelMapper;

  @Override
  public UserResponseDto createUser(UserRequestDto userRequestDto) {
    User user = this.userRepository.save(this.modelMapper.map(userRequestDto, User.class));
    return modelMapper.map(user, UserResponseDto.class);
  }

  @Override
  public UserResponseDto updateUser(UserRequestDto userRequestDto, Integer id) {

    User user =
        this.userRepository
            .findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("User", id));
    user.setPassword(userRequestDto.getPassword());
    user.setName(userRequestDto.getName());
    user.setEmail(userRequestDto.getEmail());
    user.setAbout(userRequestDto.getAbout());
    this.userRepository.save(user);
    return modelMapper.map(user, UserResponseDto.class);
  }

  @Override
  public UserResponseDto getById(Integer id) {
    User user =
        this.userRepository
            .findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("User", id));
    return modelMapper.map(user, UserResponseDto.class);
  }

  @Override
  public List<UserResponseDto> getAllUsers() {
    List<User> users = this.userRepository.findAll();
    return users.stream()
        .map(user -> modelMapper.map(user, UserResponseDto.class))
        .collect(Collectors.toList());
  }

  @Override
  public void deleteUser(Integer id) {

    User user =
        this.userRepository
            .findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("User", id));

    this.userRepository.delete(user);
  }
}
