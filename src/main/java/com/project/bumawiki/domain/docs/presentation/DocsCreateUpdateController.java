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

@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/docs/api")
public class DocsCreateUpdateController {

    private final DocsCreateService docsCreateService;
    private final DocsUpdateService docsUpdateService;

    @PostMapping("/create")
    public DocsResponseDto createDocs(@RequestHeader("Authorization")String bearer, @RequestBody DocsCreateRequestDto request){
        return docsCreateService.execute(request, bearer);
    }

    @PutMapping("/update/{id}")
    public DocsResponseDto updateDocs(@PathVariable Long id,@RequestBody DocsUpdateRequestDto request){
        UserResponseDto currentUser = docsUpdateService.findCurrentUser();
        return docsUpdateService.execute(id, currentUser ,request);
    }

}
