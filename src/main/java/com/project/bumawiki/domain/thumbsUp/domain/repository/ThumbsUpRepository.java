package com.project.bumawiki.domain.thumbsUp.domain.repository;

import com.project.bumawiki.domain.thumbsUp.domain.ThumbsUps;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ThumbsUpRepository extends JpaRepository<ThumbsUps, Long> {
}
