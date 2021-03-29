package com.app.usermanagement.service;

import com.app.usermanagement.exception.ResourceNotFoundException;
import com.app.usermanagement.model.User;
import com.app.usermanagement.payload.UpdateRequest;
import com.app.usermanagement.payload.UserProfile;
import com.app.usermanagement.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AdminService {

    @Autowired
    private UserRepository userRepository;

    public List<UserProfile> getAllUserProfiles() {
        List<UserProfile> userProfiles = userRepository.findAll().stream()
                .map(user -> new UserProfile(user.getId(), user.getUsername(), user.getName()))
                .collect(Collectors.toList());

        return userProfiles;
    }

    public UserProfile getUserProfile(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("User", "username", username));

        UserProfile userProfile = new UserProfile(user.getId(), user.getUsername(), user.getName());

        return userProfile;
    }


    public UserProfile getUserProfile(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", id));

        UserProfile userProfile = new UserProfile(user.getId(), user.getUsername(), user.getName());

        return userProfile;
    }


    public void updateUser(String username, UpdateRequest updateRequest) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("User", "username", username));
        user.setName(updateRequest.getName());
        userRepository.save(user);
    }

    public void deleteUser(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("User", "username", username));
        userRepository.delete(user);
    }
}
