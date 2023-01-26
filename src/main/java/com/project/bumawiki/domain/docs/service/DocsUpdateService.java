package com.project.bumawiki.domain.docs.service;

import com.project.bumawiki.domain.contribute.domain.Contribute;
import com.project.bumawiki.domain.contribute.service.ContributeService;
import com.project.bumawiki.domain.docs.domain.Docs;
import com.project.bumawiki.domain.docs.domain.VersionDocs;
import com.project.bumawiki.domain.docs.domain.repository.DocsRepository;
import com.project.bumawiki.domain.docs.domain.repository.VersionDocsRepository;
import com.project.bumawiki.domain.docs.domain.type.DocsType;
import com.project.bumawiki.domain.docs.exception.CannotChangeYourDocsException;
import com.project.bumawiki.domain.docs.exception.DocsNotFoundException;
import com.project.bumawiki.domain.docs.exception.NoUpdatableDocsException;
import com.project.bumawiki.domain.docs.presentation.dto.DocsResponseDto;
import com.project.bumawiki.domain.docs.presentation.dto.DocsTitleUpdateRequestDto;
import com.project.bumawiki.domain.docs.presentation.dto.DocsUpdateRequestDto;
import com.project.bumawiki.domain.image.service.ImageService;
import com.project.bumawiki.domain.user.entity.User;
import com.project.bumawiki.domain.user.entity.repository.UserRepository;
import com.project.bumawiki.domain.user.exception.UserNotLoginException;
import com.project.bumawiki.domain.user.service.UserService;
import com.project.bumawiki.global.annotation.ServiceWithTransactionalReadOnly;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;


@RequiredArgsConstructor
@ServiceWithTransactionalReadOnly
public class DocsUpdateService {
    private final DocsRepository docsRepository;
    private final VersionDocsRepository versionDocsRepository;
    private final ContributeService contributeService;
    private final ImageService imageService;
    private final UserService userService;
    private final UserRepository userRepository;

    @Transactional
    public DocsResponseDto execute(String bearer, String title, DocsUpdateRequestDto docsUpdateRequestDto, MultipartFile[] files) throws IOException {

        String authId = userService.checkIsLoginUser(bearer);


        Docs foundDocs = docsRepository.findByTitle(title)
                        .orElseThrow(() -> DocsNotFoundException.EXCEPTION);

        updateDocsOneself(foundDocs.getTitle(), foundDocs.getEnroll(), authId, foundDocs.getDocsType());

        if(files != null) {
            setImageUrlInContents(docsUpdateRequestDto, imageService.GetFileUrl(files, foundDocs.getTitle()));
        }
        VersionDocs savedVersionDocs = saveVersionDocs(docsUpdateRequestDto, foundDocs.getId());
        Docs docs = setVersionDocsToDocs(savedVersionDocs);
        docs.setModifiedTime(savedVersionDocs.getThisVersionCreatedAt());

        Contribute contribute = contributeService.updateContribute(savedVersionDocs);
        savedVersionDocs.updateContributor(contribute);

        return new DocsResponseDto(docs);
    }

    private void updateDocsOneself(String title, Integer enroll, String authId, DocsType docsType){
        User user = userRepository.findByEmail(authId)
                .orElseThrow(() -> UserNotLoginException.EXCEPTION);

        if(docsType.equals(DocsType.STUDENT)){
            if(title.equals(user.getName()) && enroll.equals(user.getEnroll())){
                throw CannotChangeYourDocsException.EXCEPTION;
            }
        } else{
            if(title.contains(user.getName())){
                throw CannotChangeYourDocsException.EXCEPTION;
            }
        }

    }


    @Transactional
    public DocsResponseDto titleUpdate(String title, DocsTitleUpdateRequestDto requestDto){
        Docs docs = docsRepository.findByTitle(title)
                .orElseThrow(() -> NoUpdatableDocsException.EXCEPTION);

        docs.updateTitle(requestDto.getTitle());

        return new DocsResponseDto(docs);
    }


    @Transactional
    private VersionDocs saveVersionDocs(DocsUpdateRequestDto docsUpdateRequestDto,Long docsId){
        return versionDocsRepository.save(
                VersionDocs.builder()
                        .docsId(docsId)
                        .thisVersionCreatedAt(LocalDateTime.now())
                        .contents(docsUpdateRequestDto.getContents())
                        .build()
        );
    }

    @Transactional
    private Docs setVersionDocsToDocs(VersionDocs versionDocs){
        Docs docs = docsRepository.findById(versionDocs.getDocsId())
                .orElseThrow(() -> NoUpdatableDocsException.EXCEPTION);

        docs.getDocsVersion().add(versionDocs);

        return docs;
    }

    /**
     * 프론트가 [사진1]이라고 보낸거 우리가 저장한 이미지 주소로 바꾸는 로직
     */
    public void setImageUrlInContents(DocsUpdateRequestDto docsUpdateRequestDto, ArrayList<String> urls){
        String content = docsUpdateRequestDto.getContents();
        for (String url : urls) {
            content = content.replaceFirst("<<사진>>",url);
        }
        docsUpdateRequestDto.updateContent(content);
    }
}

