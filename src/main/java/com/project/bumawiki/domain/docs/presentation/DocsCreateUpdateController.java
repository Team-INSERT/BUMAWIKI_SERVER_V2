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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/docs")
public class DocsCreateUpdateController {

    private final DocsUpdateService docsUpdateService;

    @PostMapping("/create")
    public ResponseEntity<DocsResponseDto> createDocs(@RequestPart DocsCreateRequestDto request, @RequestPart(required = false) MultipartFile[] files) throws IOException {
        return ResponseEntity.ok(docsCreateService.execute(request, files));
    }
    private final DocsCreateService docsCreateService;

    @PutMapping("/update/{title}")
    public DocsResponseDto updateDocs(@RequestHeader("Authorization") String bearer, @PathVariable String title, @RequestPart DocsUpdateRequestDto request, @RequestPart(required = false) MultipartFile[] files) throws IOException {
        return ResponseEntity.ok(docsUpdateService.execute(bearer, title, request, files)).getBody();
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
