package com.project.bumawiki.global;

import com.project.bumawiki.domain.contribute.domain.Contribute;
import com.project.bumawiki.domain.docs.domain.Docs;
import com.project.bumawiki.domain.docs.domain.VersionDocs;
import com.project.bumawiki.domain.docs.domain.type.DocsType;
import com.project.bumawiki.domain.user.entity.User;
import com.project.bumawiki.domain.user.entity.authority.Authority;

import java.time.LocalDateTime;

public class DataForTest {
    private final User user = User.builder()
            .id(1L)
            .name("홍길동")
            .email("checkbanworkwell@bssm.hs.kr")
            .authority(Authority.USER)
            .enroll(2022)
            .nickName("홍길동전")
            .build();

    private final Docs docs = Docs.builder()
            .id(1L)
            .title("docs title")
            .docsType(DocsType.ACCIDENT)
            .lastModifiedAt(LocalDateTime.now())
            .enroll(2022)
            .build();

    private final VersionDocs versionDocs = VersionDocs.builder()
            .id(1L)
            .docsId(1L)
            .contents("docs contents")
            .thisVersionCreatedAt(LocalDateTime.now())
            .build();

    private final Contribute contribute = Contribute
            .builder()
            .id(1L)
            .contributor(user)
            .docs(docs)
            .createdAt(LocalDateTime.now())
            .versionDocs(versionDocs)
            .build();
    public DataForTest(){
        versionDocs.updateContributor(contribute);
    }

    public User getUser() {
        return user;
    }

    public Docs getDocs() {
        return docs;
    }

    public VersionDocs getVersionDocs() {
        return versionDocs;
    }

    public Contribute getContribute() {
        return contribute;
    }
}
