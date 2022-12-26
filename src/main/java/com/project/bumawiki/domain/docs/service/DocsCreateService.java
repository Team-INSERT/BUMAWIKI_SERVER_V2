package com.project.bumawiki.domain.docs.service;

import com.project.bumawiki.domain.contribute.domain.Contribute;
import com.project.bumawiki.domain.docs.domain.Docs;
import com.project.bumawiki.domain.docs.domain.VersionDocs;
import com.project.bumawiki.domain.docs.domain.repository.DocsRepository;
import com.project.bumawiki.domain.docs.domain.repository.VersionDocsRepository;
import com.project.bumawiki.domain.docs.exception.DocsTitleAlreadyExistException;
import com.project.bumawiki.domain.user.entity.User;
import com.project.bumawiki.domain.user.exception.UserNotFoundException;
import com.project.bumawiki.global.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.project.bumawiki.domain.docs.presentation.dto.DocsResponseDto;
import com.project.bumawiki.domain.docs.presentation.dto.DocsCreateRequestDto;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class DocsCreateService {
    private final DocsRepository docsRepository;
    private final VersionDocsRepository versionDocsRepository;

    @Transactional
    public DocsResponseDto execute(DocsCreateRequestDto docsCreateRequestDto){
        checkTitleDuplication(docsCreateRequestDto.getTitle());
        Docs docs = createDocs(docsCreateRequestDto);
        VersionDocs savedDocs = saveVersionDocs(docsCreateRequestDto, docs.getId());
        setContribute(docs);

        List<VersionDocs> versionDocs = new ArrayList<>();
        versionDocs.add(savedDocs);

        docs.setVersionDocs(versionDocs);

        DocsResponseDto docsResponseDto = new DocsResponseDto(docs);
        return docsResponseDto;
    }

    @Transactional
    private VersionDocs saveVersionDocs(DocsCreateRequestDto docsCreateRequestDto, Long id){
        VersionDocs savedDocs = versionDocsRepository.save(
                VersionDocs.builder()
                        .docsId(id)
                        .contents(docsCreateRequestDto.getContents())
                        .build()
        );
        return savedDocs;
    }

    @Transactional
    private void setContribute(Docs docs) {
        User user = SecurityUtil.getCurrentUser().getUser();
        if(user == null){
            throw UserNotFoundException.EXCEPTION;
        }
        Contribute contribute = Contribute.builder()
                .docs(docs)
                .contributor(user)
                .build();
        ArrayList<Contribute> contributes = new ArrayList<>();
        contributes.add(contribute);
        docs.setContributor(contributes);
        user.setContributeDocs(contributes);
    }

    @Transactional
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
            throw DocsTitleAlreadyExistException.EXCEPTION;
    }


    /**
     * 프론트가 [사진1]이라고 보낸거 우리가 저장한 이미지 주소로 바꾸는 로직
     */
    public void setImageUrlInContents(){

    }
}


