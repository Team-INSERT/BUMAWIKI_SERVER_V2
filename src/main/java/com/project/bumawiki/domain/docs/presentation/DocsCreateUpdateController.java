package com.project.bumawiki.domain.docs.presentation;


import com.project.bumawiki.domain.docs.presentation.dto.DocsCreateRequestDto;
import com.project.bumawiki.domain.docs.presentation.dto.DocsResponseDto;
import com.project.bumawiki.domain.docs.presentation.dto.DocsUpdateRequestDto;
import com.project.bumawiki.domain.docs.service.DocsCreateService;
import com.project.bumawiki.domain.docs.service.DocsUpdateService;
import com.project.bumawiki.domain.user.presentation.dto.UserResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import reactor.util.annotation.Nullable;

import java.io.IOException;

@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/docs")
public class DocsCreateUpdateController {

    private final DocsCreateService docsCreateService;
    private final DocsUpdateService docsUpdateService;

    @PostMapping("/create")
    public DocsResponseDto createDocs(@RequestHeader("Authorization")String bearer, @RequestPart DocsCreateRequestDto request, @RequestPart(required = false) MultipartFile[] files) throws IOException {
        return docsCreateService.execute(request, bearer,files);
    }

    @PutMapping("/update/{id}")
    public DocsResponseDto updateDocs(@RequestHeader("Authorization")String bearer, @PathVariable Long id,@RequestPart DocsUpdateRequestDto request,@RequestPart(required = false) MultipartFile[] files) throws IOException {
        return docsUpdateService.execute(bearer, id, request, files);
    }
}
