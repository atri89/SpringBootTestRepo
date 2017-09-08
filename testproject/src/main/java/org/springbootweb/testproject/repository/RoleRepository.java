package org.springbootweb.testproject.repository;

import org.springbootweb.testproject.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("roleRepository")
public interface RoleRepository extends JpaRepository<Role, Long> {
	Role findByName(String roleName);
}
