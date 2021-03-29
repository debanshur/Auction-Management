package com.app.usermanagement.controller;

import com.app.usermanagement.payload.JwtAuthResponse;
import com.app.usermanagement.payload.LoginRequest;
import com.app.usermanagement.payload.SignUpRequest;
import com.app.usermanagement.payload.UserProfile;
import com.app.usermanagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;


@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/me")
    public UserProfile getCurrentUser(@RequestHeader("Authorization") String bearerToken) {
        return userService.getCurrentUser(bearerToken);
    }

    @PostMapping("/login")
    public JwtAuthResponse authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        return userService.authenticateUser(loginRequest);
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpRequest signUpRequest) {
        return userService.registerUser(signUpRequest);
    }
}
