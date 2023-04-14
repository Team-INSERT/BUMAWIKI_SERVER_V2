package com.project.bumawiki.domain.docs.domain;

import com.project.bumawiki.domain.contribute.domain.Contribute;
import com.project.bumawiki.domain.docs.domain.type.DocsType;
import com.project.bumawiki.domain.like.domain.Like;
import com.project.bumawiki.domain.like.domain.Likes;
import com.project.bumawiki.domain.like.exception.AlreadyLikeException;
import com.project.bumawiki.domain.like.exception.YouDontLikeThisDocs;
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
    private List<VersionDocs> docsVersion = new ArrayList<>();

    @OneToMany(mappedBy = "docs", cascade = CascadeType.ALL)
    private List<Contribute> contributor = new ArrayList<>();

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @Builder.Default
    private Likes likes = new Likes();

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

    public void addLike(Like like) {
        likes.addLike(like);
    }

    public int likesLength() {
        return likes.likesLength();
    }

    public boolean doesUserLike(User user) {
        return likes.doesUserLike(user);
    }

    public void cancelLike(Like like) {
        likes.cancelLike(like);
    }
}
