package com.project.bumawiki.domain.docs.service;

import com.project.bumawiki.domain.docs.domain.Docs;
import com.project.bumawiki.domain.docs.domain.VersionDocs;
import com.project.bumawiki.domain.docs.domain.repository.DocsRepository;
import com.project.bumawiki.domain.docs.domain.type.DocsType;
import com.project.bumawiki.domain.docs.exception.DocsNotFoundException;
import com.project.bumawiki.domain.docs.exception.VersionNotExistException;
import com.project.bumawiki.domain.docs.presentation.dto.response.*;
import com.project.bumawiki.domain.user.UserFacade;
import lombok.RequiredArgsConstructor;
import org.bitbucket.cowwoc.diffmatchpatch.DiffMatchPatch;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import static org.bitbucket.cowwoc.diffmatchpatch.DiffMatchPatch.Diff;


@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class DocsInformationService {
    private final DocsRepository docsRepository;
    private final UserFacade userFacade;

    public List<DocsNameAndEnrollResponseDto> findByDocsType(final DocsType docsType) {
        List<Docs> allStudent = docsRepository.findByDocsType(docsType);

        return allStudent.stream()
                .map(DocsNameAndEnrollResponseDto::new)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<DocsNameAndEnrollResponseDto> findAllByTitle(String title) {
        List<Docs> docs = docsRepository.findAllByTitle(title);

        if (docs.size() == 0) {
            throw DocsNotFoundException.EXCEPTION;
        }

        return docs.stream()
                .map(DocsNameAndEnrollResponseDto::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public DocsResponseDto findDocs(String title) {
        Docs docs = docsRepository.findByTitle(title).
                orElseThrow(() -> DocsNotFoundException.EXCEPTION);

        return new DocsResponseDto(docs)
                .setYouLikeThis(docs.doesUserThumbsUp(userFacade.getCurrentUser()));
    }

    public VersionResponseDto findDocsVersion(String title) {
        Docs docs = docsRepository.findByTitle(title)
                .orElseThrow(() -> DocsNotFoundException.EXCEPTION);

        List<VersionDocsResponseDto> versionDocs = docs.getDocsVersion()
                .stream()
                .map(VersionDocsResponseDto::new)
                .collect(Collectors.toList());

        Collections.reverse(versionDocs);

        return new VersionResponseDto(
                new DocsResponseDto(docs)
                        .setYouLikeThis(docs.doesUserThumbsUp(userFacade.getCurrentUser()))
                , versionDocs);
    }

    public List<DocsNameAndEnrollResponseDto> showDocsModifiedAtDesc(Pageable pageable) {
        return docsRepository.findByLastModifiedAt(pageable)
                .stream()
                .map(DocsNameAndEnrollResponseDto::new)
                .collect(Collectors.toList());
    }

    public List<DocsNameAndEnrollResponseDto> showDocsModifiedAtDescAll() {
        return docsRepository.findByLastModifiedAtAll()
                .stream()
                .map(DocsNameAndEnrollResponseDto::new)
                .collect(Collectors.toList());
    }

    public VersionDocsDiffResponseDto showVersionDocsDiff(String title, Long version) {
        Docs docs = docsRepository.findByTitle(title).orElseThrow(
                () -> DocsNotFoundException.EXCEPTION
        );
        String baseDocs = "";
        String versionedDocs;
        try {
            List<VersionDocs> versionDocs = docs.getDocsVersion();
            versionedDocs = versionDocs.get(version.intValue()).getContents();
            if (version > 0) {
                baseDocs = versionDocs.get((int) (version - 1)).getContents();
            }
        } catch (IndexOutOfBoundsException e) {
            throw VersionNotExistException.EXCEPTION;
        }

        DiffMatchPatch dmp = new DiffMatchPatch();
        LinkedList<Diff> diff = dmp.diffMain(baseDocs, versionedDocs);
        dmp.diffCleanupSemantic(diff);

        return new VersionDocsDiffResponseDto(new ArrayList<>(diff));
    }
}
