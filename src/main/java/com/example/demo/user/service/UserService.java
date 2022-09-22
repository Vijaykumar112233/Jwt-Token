package com.example.demo.user.service;


import com.example.demo.user.dto.UserDto;
import com.example.demo.user.entity.UserEntity;
import com.example.demo.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;


    public List<UserEntity> getUser(){
        return userRepository.findAll();
    }

    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(11);
    }

    public void saveUser(UserDto userDto){

        UserEntity userEntity = new UserEntity();

        userEntity.setEmail(userDto.getEmail());
        userEntity.setPassword(passwordEncoder().encode(userDto.getPassword()));
        userEntity.setMobile(userDto.getMobile());
        userEntity.setFirstName(userDto.getFirstName());
        userEntity.setLastName(userDto.getLastName());

        userRepository.save(userEntity);

    }

    @Transactional
    public void deleteUser(Long id){
        userRepository .deleteById(id);
    }

    public UserEntity getUserById(Long id){
        return userRepository.findById(id).get();
    }

    public List<UserEntity> getUserByName(String firstName){
        return userRepository.findByFirstNameIgnoreCase(firstName);
    }

    public List<UserEntity> getUserBySpellings(String firstName){
        return userRepository.findByFirstNameContainingIgnoreCase(firstName);
    }

    public UserEntity getUserByMobile(String number){
        return userRepository.findByMobile(number);
    }


    public Optional<UserEntity> findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }


}
