package org.springboot.jwt.controller;

import org.springboot.jwt.domain.ApplicationUser;
import org.springboot.jwt.dto.ApplicationUserDto;
import org.springboot.jwt.service.ApplicationUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private ApplicationUserService applicationUserService;


    @Autowired
    public UserController(final ApplicationUserService applicationUserService) {
        this.applicationUserService = applicationUserService;
    }

    @PostMapping("/sign-up")
    public void signUp(@RequestBody ApplicationUser applicationUser) {
        applicationUserService.save(applicationUser);
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @GetMapping("/all")
    public List<ApplicationUserDto> getAllUsers() {
        return applicationUserService.getAllUsers();
    }
}
