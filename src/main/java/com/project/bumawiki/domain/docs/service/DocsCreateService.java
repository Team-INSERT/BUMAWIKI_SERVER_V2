package com.project.bumawiki.domain.docs.service;

import com.project.bumawiki.domain.contribute.domain.Contribute;
import com.project.bumawiki.domain.docs.domain.Docs;
import com.project.bumawiki.domain.docs.domain.VersionDocs;
import com.project.bumawiki.domain.docs.domain.repository.DocsRepository;
import com.project.bumawiki.domain.docs.domain.repository.VersionDocsRepository;
import com.project.bumawiki.domain.docs.exception.PostTitleAlreadyExistException;
import com.project.bumawiki.domain.user.entity.User;
import com.project.bumawiki.global.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.project.bumawiki.domain.docs.presentation.dto.DocsResponseDto;
import com.project.bumawiki.domain.docs.presentation.dto.DocsCreateRequestDto;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class DocsCreateService {
    private final DocsRepository docsRepository;
    private final VersionDocsRepository versionDocsRepository;

    public DocsResponseDto execute(DocsCreateRequestDto docsCreateRequestDto){
        checkTitleDuplication(docsCreateRequestDto.getTitle());
        Docs docs = createDocs();
        VersionDocs savedDocs = saveVersionDocs(docsCreateRequestDto, docs.getId());
        docs.updateVersionDocs(savedDocs);
        docs.updateDocsType(docsCreateRequestDto.getDocsType());
        setContribute(docs);

        return new DocsResponseDto(savedDocs);
    }

    private VersionDocs saveVersionDocs(DocsCreateRequestDto docsCreateRequestDto, Long id){
        VersionDocs savedDocs = versionDocsRepository.save(
                VersionDocs.builder()
                        .docsId(id)
                        .title(docsCreateRequestDto.getTitle())
                        .enroll(docsCreateRequestDto.getEnroll())
                        .contents(docsCreateRequestDto.getContents())
                        .imageLink(docsCreateRequestDto.getImageLink())
                        .build()
        );
        return savedDocs;
    }

    private void setContribute(Docs docs) {
        User user = SecurityUtil.getCurrentUser().getUser();
        Contribute contribute = Contribute.builder()
                .docs(docs)
                .contributor(user)
                .build();
        docs.updateContribute(contribute);
        user.updateContribute(contribute);
    }

    private Docs createDocs(){
        return docsRepository.save(Docs.builder().build());
    }

    @Transactional(readOnly = true)
    private void checkTitleDuplication(String title) {
        versionDocsRepository.findByTitle(title)
                .orElseThrow(() -> PostTitleAlreadyExistException.EXCEPTION);
    }
}


