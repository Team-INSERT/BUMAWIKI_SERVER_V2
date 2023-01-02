package com.project.bumawiki.domain.docs.domain.repository;

import com.project.bumawiki.domain.docs.domain.Docs;
import com.project.bumawiki.domain.docs.domain.VersionDocs;
import com.project.bumawiki.domain.docs.domain.type.DocsType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface DocsRepository extends JpaRepository<Docs, Long> {

    @Query("select d from Docs d where d.docsType = :docsType order by d.enroll")
    List<Docs> findByDocsType(DocsType docsType);

    @Query("select d from Docs d where d.title like :title")
    List<Docs> findByTitle(String title);

    Optional<Docs> findById(Long id);

    @Query("select d from Docs d order by d.lastModifiedAt desc")
    Page<Docs> findByLastModifiedAt(Pageable pageable);
}
