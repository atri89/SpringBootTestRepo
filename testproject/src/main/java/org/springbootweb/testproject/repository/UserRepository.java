package org.springbootweb.testproject.repository;

import org.springbootweb.testproject.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("userRepository")
public interface UserRepository extends JpaRepository<User, Long> {
	
	User findByUserName(String userName);
	User findByEmail(String email);
	User findByConfirmationToken(String confirmationToken);
	
}
