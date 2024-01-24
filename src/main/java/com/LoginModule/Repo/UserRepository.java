package com.LoginModule.Repo;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.LoginModule.Entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	public User findByEmailAndPassword(String email, String password);

	public User findByUsername(String username);

	public Optional<User> findByEmail(String email);

}
