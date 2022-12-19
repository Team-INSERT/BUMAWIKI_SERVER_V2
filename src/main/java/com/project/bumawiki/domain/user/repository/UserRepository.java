package com.project.bumawiki.domain.user.repository;

import com.project.bumawiki.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
