package com.app.usermanagement.payload;

import javax.validation.constraints.NotBlank;


public class UpdateRequest {
    @NotBlank
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

