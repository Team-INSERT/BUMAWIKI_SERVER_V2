package com.project.bumawiki.domain.docs.service;

import com.project.bumawiki.domain.docs.domain.Docs;
import com.project.bumawiki.domain.docs.domain.repository.DocsRepository;
import com.project.bumawiki.domain.docs.domain.type.DocsType;
import com.project.bumawiki.domain.docs.presentation.dto.DocsResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
@Transactional
public class DocsInformationService {
    private final DocsRepository docsRepository;

    public List<DocsResponseDto> findAllStudent(Pageable pageable){
        List<Docs> allStudent = docsRepository.findByDocsType(DocsType.STUDENT, pageable);

        return allStudent.stream()
                .map(DocsResponseDto::new)
                .collect(Collectors.toList());
    }

    public List<DocsResponseDto> findAllAccident(Pageable pageable){
        List<Docs> allAccident = docsRepository.findByDocsType(DocsType.ACCIDENT, pageable);

        return allAccident.stream()
                .map(DocsResponseDto::new)
                .collect(Collectors.toList());
    }

    public List<DocsResponseDto> findAllTeacher(Pageable pageable){
        List<Docs> allTeacher = docsRepository.findByDocsType(DocsType.TEACHER, pageable);

        return allTeacher.stream()
                .map(DocsResponseDto::new)
                .collect(Collectors.toList());
    }

    public List<DocsResponseDto> findAllClub(Pageable pageable){
        List<Docs> allClub = docsRepository.findByDocsType(DocsType.CLUB, pageable);

        return allClub.stream()
                .map(DocsResponseDto::new)
                .collect(Collectors.toList());
    }
}


