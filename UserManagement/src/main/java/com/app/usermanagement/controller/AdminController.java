package com.app.usermanagement.controller;

import com.app.usermanagement.payload.UpdateRequest;
import com.app.usermanagement.payload.UserProfile;
import com.app.usermanagement.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @PreAuthorize("@securityService.hasKey(#secret)")
    @GetMapping("/users")
    public List<UserProfile> getAllUserProfiles(@RequestHeader("secret") String secret) {
        return adminService.getAllUserProfiles();
    }

    @PreAuthorize("@securityService.hasKey(#secret)")
    @GetMapping("/user/{username}")
    public UserProfile getUserProfile(@PathVariable(value = "username") String username,
                                      @RequestHeader("secret") String secret) {
        return adminService.getUserProfile(username);
    }

    @PreAuthorize("@securityService.hasKey(#secret)")
    @GetMapping("/user/byId/{id}")
    public UserProfile getUserProfileById(@PathVariable(value = "id") Long id,
                                          @RequestHeader("secret") String secret) {
        return adminService.getUserProfile(id);
    }

    @PreAuthorize("@securityService.hasKey(#secret)")
    @PutMapping("/user/{username}/update")
    public void updateUser(@PathVariable(value = "username") String username,
                           @RequestHeader("secret") String secret,
                           @Valid @RequestBody UpdateRequest updateRequest) {
        adminService.updateUser(username, updateRequest);
    }

    @PreAuthorize("@securityService.hasKey(#secret)")
    @PostMapping("/user/delete/{username}")
    public void deleteUser(@PathVariable(value = "username") String username,
                           @RequestHeader("secret") String secret) {
        adminService.deleteUser(username);
    }
}
