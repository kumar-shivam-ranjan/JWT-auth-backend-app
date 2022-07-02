package com.jwt.demo.payload;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.bytebuddy.implementation.bind.annotation.Empty;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserRequestDto {

  @Size(min = 3, max = 20, message = "name must contain more than 3 and less than 20 characters")
  private String name;

  @Email(message = "Must provide a valid Email")
  private String email;

  @Size(min = 8, message = "password must contain more than 8 characters")
  private String password;

  @NotEmpty private String about;
}
