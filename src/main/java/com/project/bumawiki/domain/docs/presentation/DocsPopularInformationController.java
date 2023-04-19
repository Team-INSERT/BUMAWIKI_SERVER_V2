package com.project.bumawiki.domain.docs.presentation;

import com.project.bumawiki.domain.docs.presentation.dto.response.DocsPopularResponseDto;
import com.project.bumawiki.domain.docs.service.DocsPopularInformationService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/docs")
@RequiredArgsConstructor
public class DocsPopularInformationController {
    private final DocsPopularInformationService docsPopularInformationService;

    @GetMapping("/popular")
    public Page<DocsPopularResponseDto> docsPopular(@PageableDefault(size = 20) Pageable pageable) {
        return docsPopularInformationService.getDocsByPopular(pageable);
    }
}
