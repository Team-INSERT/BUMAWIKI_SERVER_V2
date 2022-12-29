package com.project.bumawiki.domain.docs.presentation;

import com.project.bumawiki.domain.docs.presentation.dto.DocsNameAndEnrollResponseDto;
import com.project.bumawiki.domain.docs.presentation.dto.VersionResponseDto;
import com.project.bumawiki.domain.docs.presentation.dto.DocsResponseDto;
import com.project.bumawiki.domain.docs.service.DocsInformationService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/docs")
public class DocsInformationController {
    private final DocsInformationService docsInformationService;

    @GetMapping("/student")
    public List<DocsNameAndEnrollResponseDto> findAllStudent(){
        return docsInformationService.findAllStudent();
    }

    @GetMapping("/accident")
    public List<DocsNameAndEnrollResponseDto> findAllAccident(){
        return docsInformationService.findAllAccident();
    }

    @GetMapping("/teacher")
    public List<DocsNameAndEnrollResponseDto> findAllTeacher(){
        return docsInformationService.findAllTeacher();
    }

    @GetMapping("/club")
    public List<DocsNameAndEnrollResponseDto> findAllClub(){
        return docsInformationService.findAllClub();
    }



    @GetMapping("/find/title/{title}")
    public List<DocsResponseDto> findByTitle(@PathVariable String title) {
        return docsInformationService.findByTitle(title);
    }

    @GetMapping("/find/id/{id}")
    public DocsResponseDto findById(@PathVariable Long id){
        return docsInformationService.findDocs(id);
    }

    @GetMapping("/find/{id}/version")
    public VersionResponseDto showDocsVersion(@PathVariable Long id){
        return docsInformationService.findDocsVersion(id);
    }

    @GetMapping("/find/modified")
    public List<DocsResponseDto> showDocsModifiedTimeDesc(){
        return docsInformationService.showDocsModifiedAtDesc();
    }
}
