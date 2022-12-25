package com.project.bumawiki.domain.docs.service;

import com.project.bumawiki.domain.contribute.domain.Contribute;
import com.project.bumawiki.domain.docs.domain.Docs;
import com.project.bumawiki.domain.docs.domain.VersionDocs;
import com.project.bumawiki.domain.docs.domain.repository.DocsRepository;
import com.project.bumawiki.domain.docs.domain.repository.VersionDocsRepository;
import com.project.bumawiki.domain.docs.exception.NoUpdatablePostException;
import com.project.bumawiki.domain.docs.presentation.dto.DocsCreateRequestDto;
import com.project.bumawiki.domain.docs.presentation.dto.DocsResponseDto;
import com.project.bumawiki.domain.docs.presentation.dto.DocsUpdateRequestDto;
import com.project.bumawiki.domain.user.entity.User;
import com.project.bumawiki.global.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
@Transactional
public class DocsUpdateService {
    private final DocsRepository docsRepository;
    private final VersionDocsRepository versionDocsRepository;

    public DocsResponseDto execute(DocsUpdateRequestDto docsUpdateRequestDto){
        Long docsId = ifPostExistReturnPostId(docsUpdateRequestDto);
        VersionDocs savedVersionDocs = saveVersionDocs(docsUpdateRequestDto, docsId);
        Docs docs = setVersionDocsToDocs(savedVersionDocs);

        docs.updateVersionDocs(savedVersionDocs);

        setContribute(docs);

        return new DocsResponseDto(docs);
    }


    private void setContribute(Docs docs) {
        User contributor = SecurityUtil.getCurrentUser().getUser();
        Contribute contribute = Contribute.builder()
                .docs(docs)
                .contributor(contributor)
                .build();
        contributor.updateContribute(contribute);
        docs.updateContribute(contribute);
    }

    @Transactional(readOnly = true)
    private Long ifPostExistReturnPostId(DocsUpdateRequestDto docsUpdateRequestDto){
        Docs findByTitle = docsRepository.findByTitle(docsUpdateRequestDto.getTitle())
                .orElseThrow(() -> NoUpdatablePostException.EXCEPTION);

        return findByTitle.getId();
    }
    
    private VersionDocs saveVersionDocs(DocsUpdateRequestDto docsUpdateRequestDto,Long docsId){
        return versionDocsRepository.save(
                VersionDocs.builder()
                .docsId(docsId)
                .contents(docsUpdateRequestDto.getContents())
                .imageLink(docsUpdateRequestDto.getImageLink())
                .build()
        );
    }

    private Docs setVersionDocsToDocs(VersionDocs versionDocs){
        Docs docs = docsRepository.findById(versionDocs.getDocsId())
                .orElseThrow(() -> NoUpdatablePostException.EXCEPTION);

        docs.getDocsVersion().add(0, versionDocs);

        return docs;
    }
}
