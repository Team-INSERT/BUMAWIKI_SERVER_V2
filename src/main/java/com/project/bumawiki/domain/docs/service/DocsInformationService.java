package com.project.bumawiki.domain.docs.service;

import com.project.bumawiki.domain.docs.domain.Docs;
import com.project.bumawiki.domain.docs.domain.VersionDocs;
import com.project.bumawiki.domain.docs.domain.repository.DocsRepository;
import com.project.bumawiki.domain.docs.domain.type.DocsType;
import com.project.bumawiki.domain.docs.exception.DocsNotFoundException;
import com.project.bumawiki.domain.docs.exception.VersionNotExistException;
import com.project.bumawiki.domain.docs.presentation.dto.*;
import com.project.bumawiki.global.annotation.ServiceWithTransactionalReadOnly;
import lombok.RequiredArgsConstructor;
import org.bitbucket.cowwoc.diffmatchpatch.DiffMatchPatch;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.bitbucket.cowwoc.diffmatchpatch.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;


@RequiredArgsConstructor
@ServiceWithTransactionalReadOnly
public class DocsInformationService {
    private final DocsRepository docsRepository;

    public List<DocsNameAndEnrollResponseDto> findByDocsType(final DocsType docsType){
        List<Docs> allStudent = docsRepository.findByDocsType(docsType);

        return allStudent.stream()
                .map(DocsNameAndEnrollResponseDto::new)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<DocsResponseDto> findByTitle(String title){
        List<Docs> docs = docsRepository.findAllByTitle(title);
        if(docs.size() == 0){
            throw DocsNotFoundException.EXCEPTION;
        }

        return docs.stream()
                .map(DocsResponseDto::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public DocsResponseDto findDocs(String title) {
        Docs docs = docsRepository.findByTitle(title).
                orElseThrow(() -> DocsNotFoundException.EXCEPTION);

        docs.increaseView();

        return new DocsResponseDto(docs);
    }

    public VersionResponseDto findDocsVersion(String title) {
        Docs docs = docsRepository.findByTitle(title)
                .orElseThrow(() -> DocsNotFoundException.EXCEPTION);

        List<VersionDocsResponseDto> versionDocs = docs.getDocsVersion()
                .stream()
                .map(VersionDocsResponseDto::new)
                .collect(Collectors.toList());

        return new VersionResponseDto(new DocsResponseDto(docs), versionDocs);
    }

    public List<DocsNameAndEnrollResponseDto> showDocsModifiedAtDesc(Pageable pageable){
        return docsRepository.findByLastModifiedAt(pageable)
                .stream()
                .map(DocsNameAndEnrollResponseDto::new)
                .collect(Collectors.toList());
    }

    public List<DocsNameAndEnrollResponseDto> showDocsModifiedAtDescAll(){
        return docsRepository.findByLastModifiedAtAll()
                .stream()
                .map(DocsNameAndEnrollResponseDto::new)
                .collect(Collectors.toList());
    }

    public List<DocsNameAndViewResponseDto> showDocsPopular(){
        return docsRepository.findByView()
                .stream()
                .map(DocsNameAndViewResponseDto::new)
                .collect(Collectors.toList());
    }

    public VersionDocsDiffResponseDto showVersionDocsDiff(String title, int version) {
        Docs docs = docsRepository.findByTitle(title).orElseThrow(
                () -> DocsNotFoundException.EXCEPTION
        );
        try {
            String baseDocs = "";
            List<VersionDocs> versionDocs = docs.getDocsVersion();
            String versionedDocs = versionDocs.get(version).getContents();
            if (version > 0) {
                baseDocs = versionDocs.get(version - 1).getContents();
            }

            DiffMatchPatch dmp = new DiffMatchPatch();
            LinkedList<DiffMatchPatch.Diff> diff = dmp.diffMain(baseDocs, versionedDocs);
            dmp.diffCleanupSemantic(diff);

            return new VersionDocsDiffResponseDto(new ArrayList<>(diff));
        } catch (IndexOutOfBoundsException e) {
            throw VersionNotExistException.EXCEPTION;
        }
    }
}


