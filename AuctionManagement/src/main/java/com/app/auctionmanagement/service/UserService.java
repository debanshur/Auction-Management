package com.app.auctionmanagement.service;

import com.app.auctionmanagement.model.User;
import com.app.auctionmanagement.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User getUserById(Long userId) {
        return userRepository.findById(userId).get();
    }


}
