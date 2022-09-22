package com.example.demo.user.payload.request;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class SignupRequest {

//  private String username;

  private String email;

  private String mobile;

  private String firstName;

  private String middleName;

  private String lastName;

//  private Set<String> role;

  private String password;

}
