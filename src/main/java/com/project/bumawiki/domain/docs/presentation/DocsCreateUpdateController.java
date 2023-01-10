package com.project.bumawiki.domain.docs.presentation;


import com.project.bumawiki.domain.docs.presentation.dto.DocsCreateRequestDto;
import com.project.bumawiki.domain.docs.presentation.dto.DocsResponseDto;
import com.project.bumawiki.domain.docs.presentation.dto.DocsTitleUpdateRequestDto;
import com.project.bumawiki.domain.docs.presentation.dto.DocsUpdateRequestDto;
import com.project.bumawiki.domain.docs.service.DocsCreateService;
import com.project.bumawiki.domain.docs.service.DocsUpdateService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/docs")
public class DocsCreateUpdateController {

    private final DocsUpdateService docsUpdateService;

    @PostMapping("/create")
    public DocsResponseDto createDocs(@RequestHeader("Authorization")String bearer, @RequestPart DocsCreateRequestDto request, @RequestPart(required = false) MultipartFile[] files) throws IOException {
        return docsCreateService.execute(request, bearer,files);
    }
    private final DocsCreateService docsCreateService;

    @PutMapping("/update/{title}")
    public DocsResponseDto updateDocs(@RequestHeader("Authorization")String bearer, @PathVariable String title,@RequestPart DocsUpdateRequestDto request,@RequestPart(required = false) MultipartFile[] files) throws IOException {
        return docsUpdateService.execute(bearer, title, request, files);
    }

    @PutMapping("/update/title/{id}")
    public DocsResponseDto updateDocsTitle(@RequestBody DocsTitleUpdateRequestDto requestDto, @PathVariable Long id) {
        return docsUpdateService.titleUpdate(id, requestDto);
    }
}
