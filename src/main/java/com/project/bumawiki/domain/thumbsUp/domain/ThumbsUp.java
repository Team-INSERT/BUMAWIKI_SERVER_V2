package com.project.bumawiki.domain.thumbsUp.domain;

import com.project.bumawiki.domain.docs.domain.Docs;
import com.project.bumawiki.domain.thumbsUp.presentation.dto.ThumbsUpResponseDto;
import com.project.bumawiki.domain.user.entity.User;
import lombok.*;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Builder
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ThumbsUp {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "thumbs_up_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "docs_id")
    private Docs docs;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "thumbs_ups_id")
    private ThumbsUps thumbsUps;

    public boolean doesUserLikes(User user) {
        return this.user.equals(user);
    }

    public boolean doYouLike(Docs docs) {
        return this.docs.equals(docs);
    }

    public boolean equals(ThumbsUp thumbsUp) {
        return Objects.equals(user, thumbsUp.getUser()) &&
                Objects.equals(docs, thumbsUp.docs) &&
                Objects.equals(thumbsUps, thumbsUp.thumbsUps);
    }

    public ThumbsUpResponseDto getDto() {
        return new ThumbsUpResponseDto(docs.getTitle(), docs.getDocsType());
    }
}