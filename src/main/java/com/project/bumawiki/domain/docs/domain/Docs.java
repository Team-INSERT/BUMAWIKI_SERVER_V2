package com.project.bumawiki.domain.docs.domain;

import com.project.bumawiki.domain.contribute.domain.Contribute;
import com.project.bumawiki.domain.docs.domain.type.DocsType;
import lombok.*;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class Docs {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "docs_id")
    private Long id;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "versionDocs_id")
    private List<VersionDocs> docsVersion = new ArrayList<>();

    @Column(length = 8)
    @NotNull
    private int enroll;

    @Enumerated(EnumType.STRING)
    private DocsType docsType;
    @OneToMany(mappedBy = "docs")
    private List<Contribute> contributor = new ArrayList<>();
    private int view = 0;
    @LastModifiedDate
    private LocalDateTime lastModifiedAt;


    public Docs updateVersionDocs(VersionDocs versionDocs){
        docsVersion.add(0, versionDocs);
        return this;
    }

    public Docs updateContribute(Contribute contribute){
        contributor.add(0, contribute);
        return this;
    }

    public Docs updateDocsType(DocsType docsType){
        this.docsType = docsType;
        return this;
    }

    public void updateEnroll(int enroll) {
        this.enroll = enroll;
    }
}
