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
import org.springframework.web.multipart.MultipartFile;
import com.project.bumawiki.domain.image.service.StorageService;

import java.io.IOException;
import java.util.ArrayList;

@Service
@RequiredArgsConstructor
@Transactional
public class DocsCreateService {
    private final DocsRepository docsRepository;
    private final VersionDocsRepository versionDocsRepository;
    private final StorageService storageService;

    public DocsResponseDto execute(DocsCreateRequestDto docsCreateRequestDto, MultipartFile[] file, String[] ImageName) throws IOException {
        ArrayList<String> ImageURL = ImageName2Url(storageService.saveFiles(file, docsCreateRequestDto.getTitle(), ImageName));
        setImageUrlInContents(docsCreateRequestDto.getContents(),ImageURL);
        checkTitleDuplication(docsCreateRequestDto.getTitle());
        Docs docs = createDocs();
        VersionDocs savedDocs = saveVersionDocs(docsCreateRequestDto, docs.getId());
        docs.updateVersionDocs(savedDocs);
        docs.updateDocsType(docsCreateRequestDto.getDocsType());

        setContribute(docs);

        return new DocsResponseDto(docs);
    }

    private VersionDocs saveVersionDocs(DocsCreateRequestDto docsCreateRequestDto, Long id) {
        VersionDocs savedDocs = versionDocsRepository.save(
                VersionDocs.builder()
                        .docsId(id)
                        .title(docsCreateRequestDto.getTitle())
                        .enroll(docsCreateRequestDto.getEnroll())
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

    private Docs createDocs() {
        return docsRepository.save(Docs.builder().build());
    }

    @Transactional(readOnly = true)
    void checkTitleDuplication(String title) {
        versionDocsRepository.findByTitle(title)
                .orElseThrow(() -> PostTitleAlreadyExistException.EXCEPTION);
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


