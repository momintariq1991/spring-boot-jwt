package org.springboot.jwt.dto;

public class ApplicationRoleDto {

    private Long id;
    private String name;

    public Long getId() {
        return id;
    }

    public ApplicationRoleDto setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public ApplicationRoleDto setName(String name) {
        this.name = name;
        return this;
    }
}
