package org.springboot.jwt.controller;

import org.springboot.jwt.domain.ApplicationUser;
import org.springboot.jwt.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private UserDetailsServiceImpl userDetailsService;


    @Autowired
    public UserController(final UserDetailsServiceImpl userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @PostMapping("/sign-up")
    public void signUp(@RequestBody ApplicationUser applicationUser) {
        userDetailsService.save(applicationUser);
    }

    @GetMapping
    public List<ApplicationUser> getAllUsers() {
        return userDetailsService.getAllUsers();
    }
}
