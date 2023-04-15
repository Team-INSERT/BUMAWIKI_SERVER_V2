package com.project.bumawiki.domain.like.domain.repository;

import com.project.bumawiki.domain.like.domain.Likes;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikesRepository extends JpaRepository<Likes, Long> {
}
