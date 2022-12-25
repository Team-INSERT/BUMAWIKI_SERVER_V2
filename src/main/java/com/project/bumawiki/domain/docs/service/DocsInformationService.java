package com.project.bumawiki.domain.docs.service;

import com.project.bumawiki.domain.docs.domain.Docs;
import com.project.bumawiki.domain.docs.domain.repository.DocsRepository;
import com.project.bumawiki.domain.docs.domain.type.DocsType;
import com.project.bumawiki.domain.docs.exception.DocsNotFoundException;
import com.project.bumawiki.domain.docs.presentation.dto.DocsNameAndEnrollResponseDto;
import com.project.bumawiki.domain.docs.presentation.dto.DocsResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
@Transactional
public class DocsInformationService {
    private final DocsRepository docsRepository;

    public List<DocsNameAndEnrollResponseDto> findAllStudent(){
        List<Docs> allStudent = docsRepository.findByDocsType(DocsType.STUDENT);

        return allStudent.stream()
                .map(DocsNameAndEnrollResponseDto::new)
                .collect(Collectors.toList());
    }

    public List<DocsNameAndEnrollResponseDto> findAllAccident(){
        List<Docs> allAccident = docsRepository.findByDocsType(DocsType.ACCIDENT);

        return allAccident.stream()
                .map(DocsNameAndEnrollResponseDto::new)
                .collect(Collectors.toList());
    }

    public List<DocsNameAndEnrollResponseDto> findAllTeacher(){
        List<Docs> allTeacher = docsRepository.findByDocsType(DocsType.TEACHER);

        return allTeacher.stream()
                .map(DocsNameAndEnrollResponseDto::new)
                .collect(Collectors.toList());
    }

    public List<DocsNameAndEnrollResponseDto> findAllClub(){
        List<Docs> allClub = docsRepository.findByDocsType(DocsType.CLUB);

        return allClub.stream()
                .map(DocsNameAndEnrollResponseDto::new)
                .collect(Collectors.toList());
    }

    public DocsResponseDto findDocs(String title){
        Docs docs = docsRepository.findByTitle(title)
                .orElseThrow(() -> DocsNotFoundException.EXCEPTION);

        return new DocsResponseDto(docs);
    }
}


