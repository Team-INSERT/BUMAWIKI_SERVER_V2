package com.project.bumawiki.domain.docs.service;

import com.project.bumawiki.domain.contribute.domain.Contribute;
import com.project.bumawiki.domain.docs.domain.Docs;
import com.project.bumawiki.domain.docs.domain.VersionDocs;
import com.project.bumawiki.domain.docs.domain.repository.DocsRepository;
import com.project.bumawiki.domain.docs.domain.repository.VersionDocsRepository;
import com.project.bumawiki.domain.docs.exception.DocsNotFoundException;
import com.project.bumawiki.domain.docs.exception.NoUpdatablePostException;
import com.project.bumawiki.domain.docs.presentation.dto.DocsResponseDto;
import com.project.bumawiki.domain.docs.presentation.dto.DocsUpdateRequestDto;
import com.project.bumawiki.domain.image.service.StorageService;
import com.project.bumawiki.domain.user.entity.User;
import com.project.bumawiki.domain.user.entity.repository.UserRepository;
import com.project.bumawiki.domain.user.exception.UserNotLoginException;
import com.project.bumawiki.domain.user.presentation.dto.UserResponseDto;
import com.project.bumawiki.global.annotation.ServiceWithTransactionalReadOnly;
import com.project.bumawiki.global.util.SecurityUtil;
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
    private final StorageService storageService;
    private final VersionDocsRepository versionDocsRepository;

    @Transactional
    public DocsResponseDto execute(Long docsId, UserResponseDto userResponseDto, DocsUpdateRequestDto docsUpdateRequestDto, MultipartFile[] file, String[] ImageName) throws IOException {
        try {
            SecurityUtil.getCurrentUser().getUser().getAuthority();
        }catch(Exception e){
            throw UserNotLoginException.EXCEPTION;
        }
        Docs foundDocs = docsRepository.findById(docsId).
                orElseThrow(() -> DocsNotFoundException.EXCEPTION);
        if(file != null){
            ArrayList<String> Fileuri = null;
            if(file.length == 1){
                Fileuri.set(0, upLoadFile(file[0], foundDocs.getTitle(), ImageName[0]));
            }
            else {
                Fileuri = uploadMultipleFiles(file,foundDocs.getTitle(),ImageName);
            }
            setImageUrlInContents(docsUpdateRequestDto.getContents(),Fileuri);
        }

        VersionDocs savedVersionDocs = saveVersionDocs(docsUpdateRequestDto, docsId);
        Docs docs = setVersionDocsToDocs(savedVersionDocs);
        docs.setModifiedTime(savedVersionDocs.getThisVersionCreatedAt());

        setContribute(docs, userResponseDto);

        return new DocsResponseDto(docs);
    }


    @Transactional
    private void setContribute(Docs docs, UserResponseDto userResponseDto) {
        User user = SecurityUtil.getCurrentUser().getUser();
        Contribute contribute = Contribute.builder()
                .docs(docs)
                .contributor(user)
                .createdAt(LocalDateTime.now())
                .build();
        userResponseDto.updateContribute(contribute);
        docs.updateContribute(contribute);

        user.setContributeDocs(userResponseDto.getContributeDocs());
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
                .orElseThrow(() -> NoUpdatablePostException.EXCEPTION);

        docs.getDocsVersion().add(versionDocs);

        return docs;
    }

    public UserResponseDto findCurrentUser(){
        User user = SecurityUtil.getCurrentUser().getUser();

        return new UserResponseDto(user);
    }


    private String upLoadFile(MultipartFile file,String Title,String ImageName) throws IOException {
        String fileName = storageService.saveFile(file,Title,ImageName);
        return "http://10.150.150.56/image/display/"+Title+"/"+fileName;
    }
    private ArrayList<String> uploadMultipleFiles(MultipartFile[] files,String Title, String[] ImageName) throws IOException {
        ArrayList<String> ImageUrl = null;
        int i=0;
        for (MultipartFile file : files){
            ImageUrl.set(i, upLoadFile(file, Title, ImageName[i]));
            i++;
        }
        return ImageUrl;
    }

    public void setImageUrlInContents(String contents, ArrayList<String> ImageUrl) {
        for (String URL : ImageUrl) {
            contents = contents.replace("[[사진]]", URL);
        }
    }
}
