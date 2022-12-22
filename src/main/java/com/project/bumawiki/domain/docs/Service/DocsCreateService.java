package com.project.bumawiki.domain.docs.Service;

import com.project.bumawiki.domain.docs.domain.Docs;
import com.project.bumawiki.domain.docs.domain.VersionDocs;
import com.project.bumawiki.domain.docs.domain.repository.DocsRepository;
import com.project.bumawiki.domain.docs.domain.repository.VersionDocsRepository;
import com.project.bumawiki.domain.docs.exception.PostTitleAlreadyExistException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.project.bumawiki.domain.docs.presentation.dto.DocsResponseDto;
import com.project.bumawiki.domain.docs.presentation.dto.DocsCreateRequestDto;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class DocsCreateService {
    private final DocsRepository docsRepository;
    private final VersionDocsRepository versionDocsRepository;

    @Transactional
    public DocsResponseDto execute(DocsCreateRequestDto docsCreateRequestDto){
        checkTitleDuplication(docsCreateRequestDto.getTitle());
        Docs docs = createDocs();
        VersionDocs savedDocs = saveVersionDocs(docsCreateRequestDto, docs.getId());
        docs.updateVersionDocs(savedDocs);

        return new DocsResponseDto(savedDocs);
    }

    @Transactional
    protected VersionDocs saveVersionDocs(DocsCreateRequestDto docsCreateRequestDto, Long id){
        VersionDocs savedDocs = versionDocsRepository.save(
                VersionDocs.builder()
                        .DocsId(id)
                        .title(docsCreateRequestDto.getTitle())
                        .enroll(docsCreateRequestDto.getEnroll())
                        .contents(docsCreateRequestDto.getContents())
                        .imageLink(docsCreateRequestDto.getImageLink())
                        .build()
        );
        return savedDocs;
    }

    @Transactional
    protected Docs createDocs(){
        return docsRepository.save(Docs.builder().build());
    }

    private void checkTitleDuplication(String title) {
        versionDocsRepository.findByTitle(title)
                .orElseThrow(() -> PostTitleAlreadyExistException.EXCEPTION);
    }
}


