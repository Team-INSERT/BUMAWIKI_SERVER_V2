package com.project.bumawiki.domain.docs.domain.repository;

import com.project.bumawiki.domain.docs.domain.VersionDocs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface VersionDocsRepository extends JpaRepository<VersionDocs, Long> {
}
