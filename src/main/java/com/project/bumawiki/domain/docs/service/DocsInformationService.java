package com.project.bumawiki.domain.docs.service;

import com.project.bumawiki.domain.docs.domain.repository.DocsRepository;
import com.project.bumawiki.domain.docs.domain.repository.VersionDocsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class DocsInformationService {
    private final DocsRepository docsRepository;
    private final VersionDocsRepository versionDocsRepository;


}


