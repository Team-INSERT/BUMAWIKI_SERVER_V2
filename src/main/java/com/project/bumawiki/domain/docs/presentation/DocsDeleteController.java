package com.project.bumawiki.domain.docs.presentation;

import com.project.bumawiki.domain.docs.service.DocsDeleteService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/docs")
public class DocsDeleteController {
    private final DocsDeleteService docsDeleteService;

    @DeleteMapping("delete/{id}")
    public Long deleteDocs(@PathVariable Long id){
        return docsDeleteService.execute(id);
    }
}
