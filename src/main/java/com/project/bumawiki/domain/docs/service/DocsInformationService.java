package com.project.bumawiki.domain.docs.service;

import com.project.bumawiki.domain.docs.domain.Docs;
import com.project.bumawiki.domain.docs.domain.repository.DocsRepository;
import com.project.bumawiki.domain.docs.domain.repository.VersionDocsRepository;
import com.project.bumawiki.domain.docs.presentation.dto.DocsResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
@Transactional
public class DocsInformationService {
    private final DocsRepository docsRepository;
    private final VersionDocsRepository versionDocsRepository;

    public List<DocsResponseDto> allStudent(Pageable pageable){
        Page<Docs> allStudent = docsRepository.findAllStudent(pageable);

        return allStudent.stream()
                .map(DocsResponseDto::new)
                .collect(Collectors.toList());
    }

}


