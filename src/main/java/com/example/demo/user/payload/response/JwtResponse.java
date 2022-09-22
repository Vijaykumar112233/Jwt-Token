package com.example.demo.user.payload.response;

import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JwtResponse {

  private String email;
  private String accessToken;


}
