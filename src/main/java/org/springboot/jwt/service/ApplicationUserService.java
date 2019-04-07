package org.springboot.jwt.service;

import org.springboot.jwt.domain.ApplicationRole;
import org.springboot.jwt.domain.ApplicationUser;
import org.springboot.jwt.domain.CustomApplicationUser;
import org.springboot.jwt.dto.ApplicationRoleDto;
import org.springboot.jwt.dto.ApplicationUserDto;
import org.springboot.jwt.repository.ApplicationRoleRepository;
import org.springboot.jwt.repository.ApplicationUserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ApplicationUserService implements UserDetailsService {

    private final ApplicationUserRepository applicationUserRepository;
    private final ApplicationRoleRepository applicationRoleRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public ApplicationUserService(final ApplicationUserRepository applicationUserRepository,
                                  final ApplicationRoleRepository applicationRoleRepository,
                                  final BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.applicationUserRepository = applicationUserRepository;
        this.applicationRoleRepository = applicationRoleRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        ApplicationUser applicationUser = applicationUserRepository.findByUsername(username);
        if (applicationUser == null) {
            throw new UsernameNotFoundException(username);
        }
        return new CustomApplicationUser(applicationUser);
    }

    public void save(ApplicationUser applicationUser) {
        applicationUser.setPassword(bCryptPasswordEncoder.encode(applicationUser.getPassword()));
        Set<ApplicationRole> applicationRoles = applicationUser.getApplicationRoles();
        applicationUser.setApplicationRoles(null);
        applicationUserRepository.save(applicationUser);

        for (ApplicationRole applicationRole : applicationRoles) {
            applicationRole.setApplicationUser(applicationUser);
            applicationRoleRepository.save(applicationRole);
        }
    }

    public List<ApplicationUserDto> getAllUsers() {
        List<ApplicationUser> applicationUsers = applicationUserRepository.findAll();

        return applicationUsers
                .stream()
                .map(this::getDtoFromEntity)
                .collect(Collectors.toList());
    }

    private ApplicationUserDto getDtoFromEntity(ApplicationUser applicationUser) {
        Long id = applicationUser.getId();
        String username = applicationUser.getUsername();
        String password = applicationUser.getPassword();
        Set<ApplicationRole> applicationRoleSet = applicationUser.getApplicationRoles();

        return new ApplicationUserDto()
                .setId(id)
                .setUsername(username)
                .setPassword(password)
                .setRoles(applicationRoleSet.stream().map(this::getDtoFromEntity).collect(Collectors.toSet()));
    }

    private ApplicationRoleDto getDtoFromEntity(ApplicationRole applicationRole) {
        Long id = applicationRole.getId();
        String name = applicationRole.getName();

        return new ApplicationRoleDto()
                .setId(id)
                .setName(name);
    }
}
