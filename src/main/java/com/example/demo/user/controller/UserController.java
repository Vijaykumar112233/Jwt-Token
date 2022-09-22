package com.example.demo.user.controller;

import com.example.demo.user.dto.UserDto;
import com.example.demo.user.entity.UserEntity;
import com.example.demo.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/hello")
    public String hello(){
        return "hello world";
    }


    @GetMapping("/getAllUsers")
    public List<UserEntity> getUserList(){
        return userService.getUser();
    }

    @PutMapping("/updateUser")
    public String updateUser(@RequestBody UserDto userEntity){
        userService.saveUser(userEntity);
        return "User Details updated";
    }

    @DeleteMapping("/deleteUser/{id}")
    public String deleteUser(@PathVariable Long id){
        userService.deleteUser(id);
        return "Deleted successfully";
    }

    @GetMapping("/getWithId/{id}")
    public UserEntity getUserById(@PathVariable Long id) {

        return userService.getUserById(id);
    }

    @GetMapping("/getWithUsername/{name}")
    public List<UserEntity> getUserByName(@PathVariable String name){
        return userService.getUserByName(name);
    }

    @GetMapping("/getUserWithSpelling/{name}")
    public List<UserEntity> getUserByNameSpelling(@PathVariable String name){
        return userService.getUserBySpellings(name);
    }

    @GetMapping("/getWithMobile/{number}")
    public UserEntity getUserByMobile(@PathVariable String number){
        return userService.getUserByMobile(number);
    }
}
