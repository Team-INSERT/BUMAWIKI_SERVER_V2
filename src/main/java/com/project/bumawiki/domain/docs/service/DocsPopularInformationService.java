package com.project.bumawiki.domain.docs.service;

import com.project.bumawiki.domain.docs.domain.repository.DocsRepository;
import com.project.bumawiki.domain.docs.presentation.dto.response.DocsPopularResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DocsPopularInformationService {
    private final DocsRepository docsRepository;

    @Transactional(readOnly = true)
    public List<DocsPopularResponseDto> getDocsByPopular() {
        return docsRepository.findByThumbsUpsDesc();
    }
}
