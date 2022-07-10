package com.jwt.demo.service.impl;

import com.jwt.demo.constants.Constants;
import com.jwt.demo.entities.Role;
import com.jwt.demo.entities.User;
import com.jwt.demo.exception.ResourceNotFoundException;
import com.jwt.demo.payload.UserRequestDto;
import com.jwt.demo.payload.UserResponseDto;
import com.jwt.demo.repository.RoleRepository;
import com.jwt.demo.repository.UserRepository;
import com.jwt.demo.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

  @Autowired private UserRepository userRepository;
  @Autowired private ModelMapper modelMapper;

  @Autowired private RoleRepository roleRepository;

  @Autowired private PasswordEncoder passwordEncoder;

  @Override
  public UserResponseDto createUser(UserRequestDto userRequestDto) {
    User user = this.userRepository.save(this.modelMapper.map(userRequestDto, User.class));
    return modelMapper.map(user, UserResponseDto.class);
  }

  @Override
  public UserResponseDto registerNewUser(UserRequestDto userRequestDto) {
    User user = this.modelMapper.map(userRequestDto, User.class);
    user.setPassword(this.passwordEncoder.encode(user.getPassword()));
    Optional<Role> role = this.roleRepository.findById(Constants.ROLE_USER_ROLE_ID);
    user.getRoles().add(role.get());
    User savedUser = this.userRepository.save(user);
    log.info("new user:{}", user);
    log.info("saved User::{}", savedUser);
    return this.modelMapper.map(savedUser, UserResponseDto.class);
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
