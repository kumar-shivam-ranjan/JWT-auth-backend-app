package com.jwt.demo.config;

import com.jwt.demo.entities.User;
import com.jwt.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

  @Autowired private UserRepository userRepository;

  @Override
  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    User user =
        this.userRepository
            .findByEmail(email)
            .orElseThrow(() -> new UsernameNotFoundException("User: " + email + " not found"));
    CustomUserDetails customUserDetails = new CustomUserDetails(user);
    return customUserDetails;
  }
}
