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
import com.project.bumawiki.domain.docs.facade.DocsFacade;
import com.project.bumawiki.domain.docs.presentation.dto.response.DocsResponseDto;
import com.project.bumawiki.domain.docs.presentation.dto.request.DocsTitleUpdateRequestDto;
import com.project.bumawiki.domain.docs.presentation.dto.request.DocsTypeUpdateDto;
import com.project.bumawiki.domain.docs.presentation.dto.request.DocsUpdateRequestDto;
import com.project.bumawiki.domain.image.service.ImageService;
import com.project.bumawiki.domain.user.UserFacade;
import com.project.bumawiki.domain.user.entity.User;
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
    private final DocsFacade docsFacade;
    private final DocsRepository docsRepository;
    private final VersionDocsRepository versionDocsRepository;
    private final ContributeService contributeService;
    private final ImageService imageService;
    private final UserService userService;
    private final UserFacade userFacade;

    @Transactional
    public DocsResponseDto execute(String bearer, String title, DocsUpdateRequestDto docsUpdateRequestDto, MultipartFile[] files) throws IOException {
        String authId = userService.checkIsLoginUser(bearer);
        Docs foundDocs = docsRepository.findByTitle(title)
                .orElseThrow(() -> DocsNotFoundException.EXCEPTION);

        updateDocsOneself(foundDocs.getTitle(), foundDocs.getEnroll(), authId, foundDocs.getDocsType());
        updateReadOnlyDocs(foundDocs.getDocsType());

        if (files != null) {
            setImageUrlInContents(docsUpdateRequestDto, imageService.GetFileUrl(files, foundDocs.getTitle()));
        }

        VersionDocs savedVersionDocs = saveVersionDocs(docsUpdateRequestDto, foundDocs.getId());
        Docs docs = setVersionDocsToDocs(savedVersionDocs);
        docs.setModifiedTime(savedVersionDocs.getThisVersionCreatedAt());

        Contribute contribute = contributeService.updateContribute(savedVersionDocs);
        savedVersionDocs.updateContributor(contribute);

        return new DocsResponseDto(docs)
                .setYouLikeThis(docs.doesUserLike(userFacade.getCurrentUser()));
    }

    @Transactional
    public DocsResponseDto titleUpdate(String title, DocsTitleUpdateRequestDto requestDto) {

        docsFacade.checkTitleAlreadyExist(requestDto.getTitle());

        Docs docs = docsRepository.findByTitle(title)
                .orElseThrow(() -> NoUpdatableDocsException.EXCEPTION);

        docs.updateTitle(requestDto.getTitle());

        return new DocsResponseDto(docs)
                .setYouLikeThis(docs.doesUserLike(userFacade.getCurrentUser()));
    }

    private VersionDocs saveVersionDocs(DocsUpdateRequestDto docsUpdateRequestDto, Long docsId) {
        return versionDocsRepository.save(
                VersionDocs.builder()
                        .docsId(docsId)
                        .thisVersionCreatedAt(LocalDateTime.now())
                        .contents(docsUpdateRequestDto.getContents())
                        .build()
        );
    }

    private void updateDocsOneself(String title, Integer enroll, String authId, DocsType docsType) {
        User user = userFacade.getUserByEmail(authId);

        if (docsType.equals(DocsType.STUDENT)) {
            if (title.contains(user.getName()) && enroll.equals(user.getEnroll())) {
                throw CannotChangeYourDocsException.EXCEPTION;
            }
        } else {
            if (title.contains(user.getName())) {
                throw CannotChangeYourDocsException.EXCEPTION;
            }
        }

    }

    private void updateReadOnlyDocs(DocsType docsType) {

        if (docsType.equals(DocsType.READONLY)) throw NoUpdatableDocsException.EXCEPTION;
    }

    @Transactional
    public DocsResponseDto DocsTypeUpdate(final DocsTypeUpdateDto docsTypeUpdateDto) {
        Docs docs = docsRepository.findById(docsTypeUpdateDto.getId())
                .orElseThrow(() -> NoUpdatableDocsException.EXCEPTION);

        docs.updateDocsType(docsTypeUpdateDto.getDocsType());
        return new DocsResponseDto(docs)
                .setYouLikeThis(docs.doesUserLike(userFacade.getCurrentUser()));
    }


    private Docs setVersionDocsToDocs(VersionDocs versionDocs) {
        Docs docs = docsRepository.findById(versionDocs.getDocsId())
                .orElseThrow(() -> NoUpdatableDocsException.EXCEPTION);

        docs.getDocsVersion().add(versionDocs);

        return docs;
    }

    /**
     * 프론트가 [사진1]이라고 보낸거 우리가 저장한 이미지 주소로 바꾸는 로직
     */
    public void setImageUrlInContents(DocsUpdateRequestDto docsUpdateRequestDto, ArrayList<String> urls) {
        String content = docsUpdateRequestDto.getContents();
        for (String url : urls) {
            content = content.replaceFirst("<<사진>>", url);
        }
        docsUpdateRequestDto.updateContent(content);
    }
}

