package org.springboot.jwt.domain;

import javax.persistence.*;

@Entity
@Table(name = "application_role")
public class ApplicationRole {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "application_user_id", nullable = false)
    private ApplicationUser applicationUser;

    public Long getId() {
        return id;
    }

    public ApplicationRole setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public ApplicationRole setName(String name) {
        this.name = name;
        return this;
    }

    public ApplicationUser getApplicationUser() {
        return applicationUser;
    }

    public ApplicationRole setApplicationUser(ApplicationUser applicationUser) {
        this.applicationUser = applicationUser;
        return this;
    }
}
