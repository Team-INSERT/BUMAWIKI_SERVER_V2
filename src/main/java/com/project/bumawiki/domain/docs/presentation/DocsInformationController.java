package com.project.bumawiki.domain.docs.presentation;

import com.project.bumawiki.domain.docs.domain.type.DocsType;
import com.project.bumawiki.domain.docs.exception.DocsTypeNotFoundException;
import com.project.bumawiki.domain.docs.presentation.dto.*;
import com.project.bumawiki.domain.docs.service.DocsInformationService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/docs")
public class DocsInformationController {
    private final DocsInformationService docsInformationService;

    @GetMapping("/{stringDocsType}")
    public List<DocsNameAndEnrollResponseDto> findAllByDocsType(@PathVariable String stringDocsType){

        DocsType docsType = DocsType.valueOfLabel(stringDocsType);
        if(docsType == null) throw DocsTypeNotFoundException.EXCEPTION;

        return docsInformationService.findByDocsType(docsType);
    }

    @GetMapping("/find/all/title/{title}")
    public List<DocsResponseDto> findByTitle(@PathVariable String title) {
        return docsInformationService.findByTitle(title);
    }

    @GetMapping("/find/title/{title}")
    public DocsResponseDto findById(@PathVariable String title){
        return docsInformationService.findDocs(title);
    }

    @GetMapping("/find/{title}/version")
    public VersionResponseDto showDocsVersion(@PathVariable String title){
        return docsInformationService.findDocsVersion(title);
    }

    @GetMapping("/find/modified")
    public List<DocsNameAndEnrollResponseDto> showDocsModifiedTimeDesc(@PageableDefault(size = 12) Pageable pageable){
        return docsInformationService.showDocsModifiedAtDesc(pageable);
    }

    @GetMapping("/find/version/{title}/different/{version}")
    public VersionDocsDiffResponseDto showVersionDocsDiff(@PathVariable String title, @PathVariable int version) {

        return docsInformationService.showVersionDocsDiff(title,version);
    }

    @GetMapping("/find/modified/all")
    public List<DocsNameAndEnrollResponseDto> showDocsModifiedTimeDescAll(){
        return docsInformationService.showDocsModifiedAtDescAll();
    }
    @GetMapping("/find/popular")
    public List<DocsNameAndViewResponseDto> showPopularDocs(){
        return docsInformationService.showDocsPopular();
    }
}
