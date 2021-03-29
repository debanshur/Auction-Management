package com.app.auctionmanagement.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class SecurityService {

    @Value("${api.secret.key}")
    private String key;

    public boolean hasKey(String apiUserKey) {
        return key.equals(apiUserKey);
    }
}