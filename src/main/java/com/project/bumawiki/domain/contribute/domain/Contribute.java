package com.project.bumawiki.domain.contribute.domain;

import com.project.bumawiki.domain.docs.domain.Docs;
import com.project.bumawiki.domain.docs.domain.VersionDocs;
import com.project.bumawiki.domain.user.entity.User;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Contribute {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "contribute_id")
    private Long id;

    @CreatedDate
    private LocalDateTime createdAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User contributor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "docs_id")
    private Docs docs;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "version_docs_id")
    private VersionDocs versionDocs;

    @Builder
    private Contribute(final LocalDateTime createdAt, final User contributor,
                       final Docs docs, final VersionDocs versionDocs){
        this.createdAt = createdAt;
        this.contributor = contributor;
        this.docs = docs;
        this.versionDocs = versionDocs;
    }
}
