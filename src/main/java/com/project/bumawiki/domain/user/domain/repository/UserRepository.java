package com.project.bumawiki.domain.user.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.bumawiki.domain.user.domain.User;
import com.project.bumawiki.domain.user.exception.UserNotFoundException;

public interface UserRepository extends JpaRepository<User, Long> {
	Optional<User> findByEmail(String email);

	default User getById(Long id) {
		return findById(id).orElseThrow(() -> UserNotFoundException.EXCEPTION);
	}
}
