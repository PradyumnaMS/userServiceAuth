package com.auth1.authlearning.controller;

import com.auth1.authlearning.dtos.LoginRequestDto;
import com.auth1.authlearning.dtos.SignupRequestDto;
import com.auth1.authlearning.model.Token;
import com.auth1.authlearning.model.User;
import com.auth1.authlearning.repository.UserRepository;
import com.auth1.authlearning.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UserDetailsRepositoryReactiveAuthenticationManager;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/signup")
    public User signUp(@RequestBody SignupRequestDto requestDto){

        return userService.signUp(requestDto.getEmail(),
                requestDto.getPassword(),
                requestDto.getName());
    }

    @PostMapping("/login")
    public Token login(@RequestBody LoginRequestDto requestDto){
        return userService.login(requestDto.getEmail(),
                requestDto.getPassword());
    }
}
