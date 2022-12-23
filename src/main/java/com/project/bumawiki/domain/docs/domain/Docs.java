package com.project.bumawiki.domain.docs.domain;

import com.project.bumawiki.domain.contribute.domain.Contribute;
import com.project.bumawiki.domain.docs.domain.type.DocsType;
import lombok.*;

import javax.persistence.*;
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

    @Enumerated(EnumType.STRING)
    private DocsType docsType;
    @OneToMany(mappedBy = "docs")
    private List<Contribute> contributor = new ArrayList<>();

    private int view = 0;

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
}
