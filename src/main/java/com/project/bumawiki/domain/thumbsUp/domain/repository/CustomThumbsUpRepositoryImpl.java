package com.project.bumawiki.domain.thumbsUp.domain.repository;

import com.project.bumawiki.domain.thumbsUp.presentation.dto.ThumbsUpResponseDto;
import com.project.bumawiki.domain.user.domain.User;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.project.bumawiki.domain.docs.domain.QDocs.docs;
import static com.project.bumawiki.domain.thumbsUp.domain.QThumbsUp.thumbsUp;
import static com.querydsl.core.types.Projections.constructor;

@Repository
@RequiredArgsConstructor
public class CustomThumbsUpRepositoryImpl implements CustomThumbsUpRepository {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<ThumbsUpResponseDto> getUserThumbsUp(User user) {
        return jpaQueryFactory
                .select(constructor(ThumbsUpResponseDto.class, docs))
                .from(thumbsUp)
                .leftJoin(thumbsUp.docs, docs)
                .where(thumbsUp.user.eq(user))
                .distinct()
                .fetch();
    }
}
