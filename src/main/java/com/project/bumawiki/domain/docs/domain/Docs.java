package com.project.bumawiki.domain.docs.domain;

import com.project.bumawiki.domain.contribute.domain.Contribute;
import com.project.bumawiki.domain.docs.domain.type.DocsType;
import com.project.bumawiki.domain.thumbsUp.domain.ThumbsUp;
import com.project.bumawiki.domain.thumbsUp.domain.ThumbsUps;
import com.project.bumawiki.domain.user.entity.User;
import lombok.*;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Docs {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(referencedColumnName = "docs_id")
    private List<VersionDocs> docsVersion = new ArrayList<>();

    @OneToMany(mappedBy = "docs", cascade = CascadeType.ALL)
    private List<Contribute> contributor = new ArrayList<>();

    @Embedded
    @Builder.Default
    private ThumbsUps thumbsUps = new ThumbsUps();

    public void updateDocsType(DocsType docsType) {
        this.docsType = docsType;
    }

    public void setVersionDocs(List<VersionDocs> versionDocs) {
        this.docsVersion = versionDocs;
    }

    public void setContributor(List<Contribute> contributes) {
        this.contributor = contributes;
    }

    public void setModifiedTime(LocalDateTime lastModifiedAt) {
        this.lastModifiedAt = lastModifiedAt;
    }

    public void updateTitle(String title) {
        this.title = title;
    }

    public void addThumbsUp(ThumbsUp thumbsUp) {
        thumbsUps.addThumbsUp(thumbsUp);
    }

    public void firstThumbsUp(ThumbsUps thumbsUps) {
        this.thumbsUps = thumbsUps;
    }

    public boolean doesUserThumbsUp(User user) {
        return thumbsUps.doesUserThumbsUp(user);
    }

    public void cancelThumbsUp(ThumbsUp thumbsUp) {
        thumbsUps.cancelLike(thumbsUp);
    }

    public int getThumbsUpsCount() {
        if (thumbsUps == null) {
            return 0;
        }
        return thumbsUps.getThumbsUpsCount();
    }
}
