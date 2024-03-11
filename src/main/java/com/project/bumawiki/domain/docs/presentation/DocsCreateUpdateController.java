package com.project.bumawiki.domain.docs.presentation;


import com.project.bumawiki.domain.docs.presentation.dto.request.DocsCreateRequestDto;
import com.project.bumawiki.domain.docs.presentation.dto.request.DocsTitleUpdateRequestDto;
import com.project.bumawiki.domain.docs.presentation.dto.request.DocsTypeUpdateDto;
import com.project.bumawiki.domain.docs.presentation.dto.request.DocsUpdateRequestDto;
import com.project.bumawiki.domain.docs.presentation.dto.response.DocsResponseDto;
import com.project.bumawiki.domain.docs.service.DocsCreateService;
import com.project.bumawiki.domain.docs.service.DocsUpdateService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
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
    private final DocsCreateService docsCreateService;

    @PostMapping("/create")
    public ResponseEntity<DocsResponseDto> createDocs(@RequestBody DocsCreateRequestDto request) throws IOException {
        return ResponseEntity.ok(docsCreateService.execute(request));
    }

    @PutMapping("/update/{title}")
    public DocsResponseDto updateDocs(@RequestHeader("Authorization") String bearer, @PathVariable String title, @RequestBody DocsUpdateRequestDto request) throws IOException {
        return ResponseEntity.ok(docsUpdateService.execute(bearer, title, request)).getBody();
    }

    @PutMapping("/update/title/{title}")
    public ResponseEntity<DocsResponseDto> updateDocsTitle(@RequestBody DocsTitleUpdateRequestDto requestDto, @PathVariable String title) {
        return ResponseEntity.ok(docsUpdateService.titleUpdate(title, requestDto));
    }

    @PutMapping("/update/docsType")
    public ResponseEntity<DocsResponseDto> updateDocsType(@RequestBody DocsTypeUpdateDto requestDto) {
        return ResponseEntity.ok(docsUpdateService.DocsTypeUpdate(requestDto));
    }
}
