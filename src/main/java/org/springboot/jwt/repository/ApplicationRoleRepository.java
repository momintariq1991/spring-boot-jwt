package org.springboot.jwt.repository;

import org.springboot.jwt.domain.ApplicationRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApplicationRoleRepository extends JpaRepository<ApplicationRole, Long> {
}
