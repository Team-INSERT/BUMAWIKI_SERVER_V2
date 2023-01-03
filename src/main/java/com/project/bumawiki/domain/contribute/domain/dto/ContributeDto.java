package com.project.bumawiki.domain.contribute.domain.dto;

import com.project.bumawiki.domain.contribute.domain.Contribute;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ContributeDto {

    private Long userId;
    private String userNickName;
    private Long docsId;
    private LocalDateTime createTime;
    private String title;
    private Long versionDocsId;

    public ContributeDto(Contribute contribute){
        this.userId = contribute.getContributor().getId();
        this.userNickName = contribute.getContributor().getNickName();
        this.docsId = contribute.getDocs().getId();
        this.createTime = contribute.getCreatedAt();
        this.title = contribute.getDocs().getTitle();
        this.versionDocsId = contribute.getVersionDocs().getId();
        int a;
    }
}
