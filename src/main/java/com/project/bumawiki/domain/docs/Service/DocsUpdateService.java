package com.project.bumawiki.domain.docs.Service;

import com.project.bumawiki.domain.docs.domain.repository.VersionDocsRepository;
import com.project.bumawiki.domain.docs.presentation.dto.DocsResponseDto;
import com.project.bumawiki.domain.docs.presentation.dto.DocsCreateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class DocsUpdateService {
    private final DocsResponseDto docsResponseDto;
    private final VersionDocsRepository versionDocsRepository;

    public DocsResponseDto execute(DocsCreateRequestDto docsCreateRequestDto){
        versionDocsRepository.findByTitle(docsCreateRequestDto.getTitle())
    }
}
