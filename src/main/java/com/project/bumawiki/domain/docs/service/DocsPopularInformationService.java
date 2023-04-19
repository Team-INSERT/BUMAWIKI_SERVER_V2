package com.project.bumawiki.domain.docs.service;

import com.project.bumawiki.domain.docs.domain.repository.DocsRepository;
import com.project.bumawiki.domain.docs.presentation.dto.response.DocsPopularResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DocsPopularInformationService {
    private final DocsRepository docsRepository;

    @Transactional(readOnly = true)
    public Page<DocsPopularResponseDto> getDocsByPopular(Pageable pageable) {
        List<DocsPopularResponseDto> collect = docsRepository.findByThumbsUpsDesc(pageable)
                .stream()
                .map(DocsPopularResponseDto::new)
                .collect(Collectors.toList());

        return new PageImpl<>(collect);
    }
}