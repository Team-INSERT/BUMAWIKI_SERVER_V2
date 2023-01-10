package com.project.bumawiki.domain.docs.presentation;

import com.project.bumawiki.domain.docs.presentation.dto.DocsNameAndEnrollResponseDto;
import com.project.bumawiki.domain.docs.presentation.dto.VersionResponseDto;
import com.project.bumawiki.domain.docs.presentation.dto.DocsResponseDto;
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

    @GetMapping("/majorTeacher")
    public List<DocsNameAndEnrollResponseDto> findAllMajorTeacher(){
        return docsInformationService.findAllMajorTeacher();
    }

    @GetMapping("/mentorTeacher")
    public List<DocsNameAndEnrollResponseDto> findAllMentorTeacher(){
        return docsInformationService.findAllMentorTeacher();
    }

    @GetMapping("/club")
    public List<DocsNameAndEnrollResponseDto> findAllClub(){
        return docsInformationService.findAllClub();
    }

    @GetMapping("/freeClub")
    public List<DocsNameAndEnrollResponseDto> findAllFreeClub(){
        return docsInformationService.findALLFreeClub();
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
    public List<DocsResponseDto> showDocsModifiedTimeDesc(@PageableDefault(size = 10) Pageable pageable){
        return docsInformationService.showDocsModifiedAtDesc(pageable);
    }
}
