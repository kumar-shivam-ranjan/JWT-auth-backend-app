package com.jwt.demo.payload;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class JwtAuthRequest {
    private String username;
    private String password;
}
