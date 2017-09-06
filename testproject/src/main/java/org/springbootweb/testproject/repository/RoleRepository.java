package org.springbootweb.testproject.repository;

import org.springbootweb.testproject.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
}
