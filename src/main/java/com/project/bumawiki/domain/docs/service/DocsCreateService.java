package com.project.bumawiki.domain.docs.service;

import com.project.bumawiki.domain.contribute.domain.Contribute;
import com.project.bumawiki.domain.contribute.service.ContributeService;
import com.project.bumawiki.domain.docs.domain.Docs;
import com.project.bumawiki.domain.docs.domain.VersionDocs;
import com.project.bumawiki.domain.docs.domain.repository.DocsRepository;
import com.project.bumawiki.domain.docs.domain.repository.VersionDocsRepository;
import com.project.bumawiki.domain.docs.presentation.dto.DocsCreateRequestDto;
import com.project.bumawiki.domain.docs.presentation.dto.DocsResponseDto;
import com.project.bumawiki.domain.image.service.ImageService;
import com.project.bumawiki.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class DocsCreateService {
    private final DocsRepository docsRepository;
    private final VersionDocsRepository versionDocsRepository;
    private final ImageService imageService;
    private final ContributeService contributeService;
    private final UserService userService;

    @Transactional
    public DocsResponseDto execute(DocsCreateRequestDto docsCreateRequestDto, String bearer, MultipartFile[] files) throws IOException {

        userService.checkIsLoginUser(bearer);

        if(files != null ){
            ArrayList<String> FileUrl = imageService.GetFileUrl(files, docsCreateRequestDto.getTitle());
            setImageUrlInContents(docsCreateRequestDto,FileUrl);
        }

        Docs docs = createDocs(docsCreateRequestDto);
        VersionDocs savedVersionDocs = saveVersionDocs(docsCreateRequestDto, docs.getId());
        Contribute contribute = contributeService.setContribute(savedVersionDocs);
        List<VersionDocs> versionDocs = new ArrayList<>();
        versionDocs.add(savedVersionDocs);

        savedVersionDocs.updateContributor(contribute);

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
                        .thisVersionCreatedAt(LocalDateTime.now())
                        .build()
        );
        return savedDocs;
    }

    @Transactional
    private Docs createDocs(DocsCreateRequestDto docsCreateRequestDto) {
        return docsRepository.save(
                Docs.builder()
                        .title(docsCreateRequestDto.getTitle())
                        .enroll(docsCreateRequestDto.getEnroll())
                        .docsType(docsCreateRequestDto.getDocsType())
                        .lastModifiedAt(LocalDateTime.now())
                        .build()
        );
    }


    /**
     * 프론트가 [사진1]이라고 보낸거 우리가 저장한 이미지 주소로 바꾸는 로직
     */
    public void setImageUrlInContents(DocsCreateRequestDto docsCreateRequestDto,ArrayList<String> urls){
        String content = docsCreateRequestDto.getContents();
        for (String url : urls) {
            content = content.replace("<<사진>>",url);
        }
        docsCreateRequestDto.updateContent(content);
    }
}


