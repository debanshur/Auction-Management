package com.app.usermanagement.service;

import com.app.usermanagement.exception.ResourceNotFoundException;
import com.app.usermanagement.model.Role;
import com.app.usermanagement.model.User;
import com.app.usermanagement.payload.ApiResponse;
import com.app.usermanagement.payload.JwtAuthResponse;
import com.app.usermanagement.payload.LoginRequest;
import com.app.usermanagement.payload.SignUpRequest;
import com.app.usermanagement.payload.UserProfile;
import com.app.usermanagement.repository.UserRepository;
import com.app.usermanagement.security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {


    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    JwtTokenProvider tokenProvider;

    public UserProfile getCurrentUser(String bearerToken) {
        User user = tokenProvider.getUserDetails(bearerToken);
        UserProfile userProfile = new UserProfile(user.getId(), user.getUsername(), user.getName());

        return userProfile;
    }

    public JwtAuthResponse authenticateUser(LoginRequest loginRequest) {
        if (!userRepository.existsByUsername(loginRequest.getUsernameOrEmail()) &&
                !userRepository.existsByEmail(loginRequest.getUsernameOrEmail())) {
            throw new ResourceNotFoundException("User", "username", loginRequest.getUsernameOrEmail());
        }

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsernameOrEmail(),
                        loginRequest.getPassword()
                )
        );

        String jwt = tokenProvider.generateToken(authentication);
        return new JwtAuthResponse(jwt);
    }

    public ResponseEntity<ApiResponse> registerUser(SignUpRequest signUpRequest) {
        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            return ResponseEntity.badRequest().body(new ApiResponse(HttpStatus.BAD_REQUEST,
                    "Username already taken. Try another"));
        }

        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity.badRequest().body(new ApiResponse(HttpStatus.BAD_REQUEST,
                    "Email already registered"));
        }

        User user = new User(signUpRequest.getName(), signUpRequest.getUsername(),
                signUpRequest.getEmail(), signUpRequest.getPassword());

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        if (signUpRequest.getIsAdmin()) {
            user.setRole(Role.ADMIN);
        } else {
            user.setRole(Role.USER);
        }

        userRepository.save(user);

        return ResponseEntity.ok().body(new ApiResponse(HttpStatus.OK,
                "User registration successfull"));
    }
}
