package com.jwt.demo;

import com.jwt.demo.constants.Constants;
import com.jwt.demo.entities.Role;
import com.jwt.demo.repository.RoleRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class JwtAuthenticationBackendApplication implements CommandLineRunner {

  @Autowired private RoleRepository roleRepository;

  public static void main(String[] args) {
    SpringApplication.run(JwtAuthenticationBackendApplication.class, args);
  }

  @Bean
  public ModelMapper modelMapper() {
    return new ModelMapper();
  }

  @Override
  public void run(String... args) throws Exception {
    try {

      Role admin = new Role();
      admin.setId(Constants.ROLE_ADMIN_ROLE_ID);
      admin.setName("ROLE_ADMIN");

      Role user = new Role();
      user.setId(Constants.ROLE_USER_ROLE_ID);
      user.setName("ROLE_ADMIN");

      roleRepository.save(admin);
      roleRepository.save(user);


    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
