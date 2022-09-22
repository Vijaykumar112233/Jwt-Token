package com.example.demo.user.controller;

import com.example.demo.user.dto.UserDto;
import com.example.demo.user.entity.UserEntity;
import com.example.demo.user.jwt.JwtTokenUtil;
import com.example.demo.user.payload.request.LoginRequest;
import com.example.demo.user.payload.response.JwtResponse;
import com.example.demo.user.repository.UserRepository;
import com.example.demo.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class UserAuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserService userService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @PostMapping("/register")
    public String register(@RequestBody UserDto userDto){
        userService.saveUser(userDto);
        return "register success";
    }


    @PostMapping("/signin")
    public ResponseEntity<Object> login(@RequestBody LoginRequest loginRequest) throws Exception {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getEmail(),loginRequest.getPassword())
            );

            UserEntity userEntity = (UserEntity) authentication.getPrincipal();

            String accessToken = jwtTokenUtil.generateAccessToken(userEntity);
            JwtResponse response = new JwtResponse(userEntity.getEmail(),accessToken);

            return ResponseEntity.ok(response);

        } catch (BadCredentialsException ex){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
}
