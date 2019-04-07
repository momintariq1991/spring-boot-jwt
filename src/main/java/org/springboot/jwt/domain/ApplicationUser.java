package org.springboot.jwt.domain;

import javax.persistence.*;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Table(name = "application_user")
public class ApplicationUser {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String username;
    private String password;
    @OneToMany(mappedBy = "applicationUser", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private Set<ApplicationRole> applicationRoles;

    public ApplicationUser() {}

    public ApplicationUser(ApplicationUser applicationUser) {
        this.id = applicationUser.getId();
        this.username = applicationUser.getUsername();
        this.password = applicationUser.getPassword();
        this.applicationRoles = applicationUser.getApplicationRoles();
    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<ApplicationRole> getApplicationRoles() {
        return applicationRoles;
    }

    public ApplicationUser setApplicationRoles(Set<ApplicationRole> applicationRoles) {
        this.applicationRoles = applicationRoles;
        return this;
    }

    public String getRolesAsString() {
        List<String> list = this.getApplicationRoles()
                .stream()
                .map(ApplicationRole::getName)
                .collect(Collectors.toList());
        return String.join(",", list);
    }
}
