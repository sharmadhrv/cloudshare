package com.dhruvlab.cloudshare.controller;

import com.dhruvlab.cloudshare.dto.LoginRequestDto;
import com.dhruvlab.cloudshare.dto.RegisterRequestDto;
import com.dhruvlab.cloudshare.service.UserServiceImplementation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final  UserServiceImplementation userServiceImplementation;

    @Autowired
    public AuthController(UserServiceImplementation userServiceImplementation) {
        this.userServiceImplementation = userServiceImplementation;
    }

    @PostMapping("/register")
    public String registerUser(@Valid @RequestBody RegisterRequestDto registerRequestDto){
       return userServiceImplementation.registerUser(registerRequestDto);
    }

    @PostMapping("/login")
    public String loginUser(@RequestBody LoginRequestDto loginRequestDto) {
        return userServiceImplementation.loginUser(loginRequestDto);
    }

}
