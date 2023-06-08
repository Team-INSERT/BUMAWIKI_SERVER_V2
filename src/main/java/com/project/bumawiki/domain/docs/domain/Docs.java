package com.project.bumawiki.domain.docs.domain;

import com.project.bumawiki.domain.contribute.domain.Contribute;
import com.project.bumawiki.domain.docs.domain.type.DocsType;
import com.project.bumawiki.domain.thumbsUp.domain.ThumbsUp;
import com.project.bumawiki.domain.thumbsUp.domain.collection.DocsThumbsUps;
import com.project.bumawiki.domain.user.domain.User;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
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
    private List<VersionDocs> docsVersion = new ArrayList<>();

    @OneToMany(mappedBy = "docs", cascade = CascadeType.ALL)
    private List<Contribute> contributor = new ArrayList<>();

    @Embedded
    @Builder.Default
    private DocsThumbsUps docsThumbsUp = new DocsThumbsUps();

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
        docsThumbsUp.addThumbsUp(thumbsUp);
    }

    public boolean doesUserThumbsUp(User user) {
        return docsThumbsUp.doesUserThumbsUp(user);
    }

    public void cancelThumbsUp(ThumbsUp thumbsUp) {
        docsThumbsUp.cancelLike(thumbsUp);
    }

    public boolean doesUserLike(User user) {
        return docsThumbsUp.doesUserThumbsUp(user);
    }

    public int getThumbsUpsCount() {
        if (docsThumbsUp == null) {
            return 0;
        }
        return docsThumbsUp.getThumbsUpsCount();
    }
}
