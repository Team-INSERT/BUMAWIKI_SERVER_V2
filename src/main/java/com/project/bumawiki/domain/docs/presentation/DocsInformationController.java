package com.project.bumawiki.domain.docs.presentation;

import com.project.bumawiki.domain.docs.presentation.dto.DocsNameAndEnrollResponseDto;
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

    @GetMapping("/find/{enroll}/{title}/")
    public List<DocsResponseDto> findDocs(@PathVariable String title, @PathVariable int enroll){
        return docsInformationService.findDocs(title, enroll);
    }


}
