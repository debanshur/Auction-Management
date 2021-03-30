package com.app.usermanagement.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class SecurityService {

    @Value("${api.secret.key}")
    private String key;

    public boolean validateKey(String apiUserKey) {
        return key.equals(apiUserKey);
    }
}