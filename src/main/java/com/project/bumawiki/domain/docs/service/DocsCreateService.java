package com.project.bumawiki.domain.docs.service;

import com.project.bumawiki.domain.contribute.domain.Contribute;
import com.project.bumawiki.domain.docs.domain.Docs;
import com.project.bumawiki.domain.docs.domain.VersionDocs;
import com.project.bumawiki.domain.docs.domain.repository.DocsRepository;
import com.project.bumawiki.domain.docs.domain.repository.VersionDocsRepository;
import com.project.bumawiki.domain.user.entity.User;
import com.project.bumawiki.domain.user.exception.UserNotFoundException;
import com.project.bumawiki.domain.user.exception.UserNotLoginException;
import com.project.bumawiki.global.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.project.bumawiki.domain.docs.presentation.dto.DocsResponseDto;
import com.project.bumawiki.domain.docs.presentation.dto.DocsCreateRequestDto;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import com.project.bumawiki.domain.image.service.StorageService;

import javax.validation.constraints.Null;
import java.io.IOException;
import java.util.ArrayList;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class DocsCreateService {
    private final DocsRepository docsRepository;
    private final VersionDocsRepository versionDocsRepository;
    private final StorageService storageService;

    @Transactional
    public DocsResponseDto execute(DocsCreateRequestDto docsCreateRequestDto){

        Docs docs = createDocs(docsCreateRequestDto);
    public DocsResponseDto execute(DocsCreateRequestDto docsCreateRequestDto, MultipartFile[] file, String[] ImageName) throws IOException {
        if(file != null){
            ArrayList<String> ImageURL = ImageName2Url(storageService.saveFiles(file, docsCreateRequestDto.getTitle(), ImageName));
            setImageUrlInContents(docsCreateRequestDto.getContents(),ImageURL);
        }
        checkTitleDuplication(docsCreateRequestDto.getTitle());
        Docs docs = createDocs();
        VersionDocs savedDocs = saveVersionDocs(docsCreateRequestDto, docs.getId());
        docs.updateVersionDocs(savedDocs);
        docs.updateDocsType(docsCreateRequestDto.getDocsType());

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
    private Docs createDocs(DocsCreateRequestDto docsCreateRequestDto) {
        return docsRepository.save(
                Docs.builder()
                        .title(docsCreateRequestDto.getTitle())
                        .enroll(docsCreateRequestDto.getEnroll())
                        .docsType(docsCreateRequestDto.getDocsType())
                        .build()
        );
    }


    private ArrayList<String> ImageName2Url(ArrayList<String> ImageUrl) {

        for (int i = 0; i < ImageUrl.size(); i++) {
            ImageUrl.set(i, "대충 경로" + "/image/display/" + ImageUrl);
        }
        return ImageUrl;
    }


    /**
     * 프론트가 [사진1]이라고 보낸거 우리가 저장한 이미지 주소로 바꾸는 로직
     */

    public void setImageUrlInContents(String contents, ArrayList<String> ImageUrl) {
        for (String URL : ImageUrl) {
            contents = contents.replace("[[사진]]", URL);
        }
    }
}


