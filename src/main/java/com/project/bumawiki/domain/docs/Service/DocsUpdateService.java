package com.project.bumawiki.domain.docs.Service;

import com.project.bumawiki.domain.docs.domain.Docs;
import com.project.bumawiki.domain.docs.domain.VersionDocs;
import com.project.bumawiki.domain.docs.domain.repository.DocsRepository;
import com.project.bumawiki.domain.docs.domain.repository.VersionDocsRepository;
import com.project.bumawiki.domain.docs.exception.NoUpdatablePostException;
import com.project.bumawiki.domain.docs.presentation.dto.DocsResponseDto;
import com.project.bumawiki.domain.docs.presentation.dto.DocsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

// 기여자 생성 해야함

@RequiredArgsConstructor
@Service
public class DocsUpdateService {
    private final DocsRepository docsRepository;
    private final VersionDocsRepository versionDocsRepository;

    public DocsResponseDto execute(DocsUpdateRequestDto docsUpdateRequestDto){
        VersionDocs versionDocs = ifPostExistReturnPostId(docsUpdateRequestDto);
        VersionDocs savedVersionDocs = saveVersionDocs(docsUpdateRequestDto, versionDocs);
        setVersionDocsToDocs(savedVersionDocs);

        return new DocsResponseDto(savedVersionDocs);
    }

    public VersionDocs ifPostExistReturnPostId(DocsUpdateRequestDto docsUpdateRequestDto){
        List<VersionDocs> findVersionDocs = versionDocsRepository.findAllByTitle(docsUpdateRequestDto.getTitle());
        if(findVersionDocs.size() == 0){
            throw NoUpdatablePostException.EXCEPTION;
        }
        return findVersionDocs.get(0);
    }
    
    public VersionDocs saveVersionDocs(DocsUpdateRequestDto docsUpdateRequestDto, VersionDocs versionDocs){
        return versionDocsRepository.save(
                VersionDocs.builder()
                .docsId(versionDocs.getDocsId())
                .title(docsUpdateRequestDto.getTitle())
                .enroll(docsUpdateRequestDto.getEnroll())
                .contents(docsUpdateRequestDto.getContents())
                .imageLink(docsUpdateRequestDto.getImageLink())
                .build()
        );
    }

    public void setVersionDocsToDocs(VersionDocs versionDocs){
        Docs docs = docsRepository.findById(versionDocs.getDocsId())
                .orElseThrow(() -> NoUpdatablePostException.EXCEPTION);

        docs.getDocsVersion().add(0, versionDocs);
    }
}
