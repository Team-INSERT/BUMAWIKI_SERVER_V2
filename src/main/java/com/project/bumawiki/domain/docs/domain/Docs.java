package com.project.bumawiki.domain.docs.domain;

import com.project.bumawiki.domain.contribute.domain.Contribute;
import com.project.bumawiki.domain.docs.domain.type.DocsType;
import lombok.*;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Docs {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "docs_id")
    private Long id;

    @Column(length = 32, unique = true)
    private String title;

    @Column(length = 8)
    private int enroll;

    @Enumerated(EnumType.STRING)
    private DocsType docsType;

    @LastModifiedDate
    private LocalDateTime lastModifiedAt;

    private int view = 0;

    @Builder
    private Docs(final String title, final int enroll,
                   final DocsType docsType, final LocalDateTime lastModifiedAt){
        this.title = title;
        this.enroll = enroll;
        this.docsType = docsType;
        this.lastModifiedAt = lastModifiedAt;
    }

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<VersionDocs> docsVersion = new ArrayList<>();

    @OneToMany(mappedBy = "docs", cascade = CascadeType.ALL)
    private List<Contribute> contributor = new ArrayList<>();

    public void updateDocsType(DocsType docsType) {
        this.docsType = docsType;
    }
    public void setVersionDocs(List<VersionDocs> versionDocs){
        this.docsVersion = versionDocs;
    }
    public void setContributor(List<Contribute> contributes){
        this.contributor =  contributes;
    }
    public void increaseView(){
        this.view += 1;
    }
    public void setModifiedTime(LocalDateTime lastModifiedAt){
        this.lastModifiedAt = lastModifiedAt;
    }
    public void updateTitle(String title){
        this.title = title;
    }
}
