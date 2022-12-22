package com.project.bumawiki.domain.docs.Service;

import com.project.bumawiki.domain.docs.domain.Docs;
import com.project.bumawiki.domain.docs.domain.VersionDocs;
import com.project.bumawiki.domain.docs.domain.repository.DocsRepository;
import com.project.bumawiki.domain.docs.domain.repository.VersionDocsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.project.bumawiki.domain.docs.presentation.dto.DocsResponseDto;
import com.project.bumawiki.domain.docs.presentation.dto.VersionDocsCreateDto;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class DocsCreateService {
    private final DocsRepository docsRepository;
    private final VersionDocsRepository versionDocsRepository;

    @Transactional
    public DocsResponseDto execute(VersionDocsCreateDto versionDocsCreateDto){
        Docs docs = createDocs();
        VersionDocs savedDocs = saveVersionDocs(versionDocsCreateDto, docs.getId());
        docs.updateVersionDocs(savedDocs);

        return new DocsResponseDto(savedDocs);
    }

    @Transactional
    protected VersionDocs saveVersionDocs(VersionDocsCreateDto versionDocsCreateDto, Long id){
        VersionDocs savedDocs = versionDocsRepository.save(
                VersionDocs.builder()
                        .DocsId(id)
                        .title(versionDocsCreateDto.getTitle())
                        .contents(versionDocsCreateDto.getContents())
                        .imageLink(versionDocsCreateDto.getImageLink())
                        .build()
        );
        return savedDocs;
    }

    @Transactional
    protected Docs createDocs(){
        return docsRepository.save(Docs.builder().build());
    }
}
