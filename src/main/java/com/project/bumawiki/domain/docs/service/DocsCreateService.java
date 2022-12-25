package com.project.bumawiki.domain.docs.service;

import com.project.bumawiki.domain.contribute.domain.Contribute;
import com.project.bumawiki.domain.docs.domain.Docs;
import com.project.bumawiki.domain.docs.domain.VersionDocs;
import com.project.bumawiki.domain.docs.domain.repository.DocsRepository;
import com.project.bumawiki.domain.docs.domain.repository.VersionDocsRepository;
import com.project.bumawiki.domain.docs.domain.type.DocsType;
import com.project.bumawiki.domain.docs.exception.PostTitleAlreadyExistException;
import com.project.bumawiki.domain.user.entity.User;
import com.project.bumawiki.global.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.project.bumawiki.domain.docs.presentation.dto.DocsResponseDto;
import com.project.bumawiki.domain.docs.presentation.dto.DocsCreateRequestDto;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class DocsCreateService {
    private final DocsRepository docsRepository;
    private final VersionDocsRepository versionDocsRepository;

    public DocsResponseDto execute(DocsCreateRequestDto docsCreateRequestDto){
        checkTitleDuplication(docsCreateRequestDto.getTitle());
        Docs docs = createDocs(docsCreateRequestDto);
        VersionDocs savedDocs = saveVersionDocs(docsCreateRequestDto, docs.getId());
        setContribute(docs);

        docs.updateVersionDocs(savedDocs);

        return new DocsResponseDto(docs);
    }

    private VersionDocs saveVersionDocs(DocsCreateRequestDto docsCreateRequestDto, Long id){
        VersionDocs savedDocs = versionDocsRepository.save(
                VersionDocs.builder()
                        .docsId(id)
                        .contents(docsCreateRequestDto.getContents())
                        .imageLink(docsCreateRequestDto.getImage())
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

    private Docs createDocs(DocsCreateRequestDto docsCreateRequestDto){
        return docsRepository.save(
                Docs.builder()
                        .title(docsCreateRequestDto.getTitle())
                        .enroll(docsCreateRequestDto.getEnroll())
                        .docsType(docsCreateRequestDto.getDocsType())
                .build()
        );
    }

    @Transactional(readOnly = true)
    private void checkTitleDuplication(String title) {
        Optional<Docs> docs = docsRepository.findByTitle(title);
        if(docs == null)
            throw PostTitleAlreadyExistException.EXCEPTION;
    }


    /**
     * 프론트가 [사진1]이라고 보낸거 우리가 저장한 이미지 주소로 바꾸는 로직
     */
    public void setImageUrlInContents(){

    }
}


