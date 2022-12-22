package com.project.bumawiki.domain.docs.domain.repository;

import com.project.bumawiki.domain.docs.domain.VersionDocs;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VersionDocsRepository extends JpaRepository<VersionDocs, Long> {
    Optional<VersionDocs> findByTitle(String title);
}
