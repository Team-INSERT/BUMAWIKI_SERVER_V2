package com.project.bumawiki.domain.thumbsUp.domain.repository;

import com.project.bumawiki.domain.thumbsUp.presentation.dto.ThumbsUpResponseDto;
import com.project.bumawiki.domain.user.entity.User;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.project.bumawiki.domain.docs.domain.QDocs.*;
import static com.project.bumawiki.domain.thumbsUp.domain.QThumbsUp.*;

@Repository
@RequiredArgsConstructor
public class CustomThumbsUpRepositoryImpl implements CustomThumbsUpRepository {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<ThumbsUpResponseDto> getUserThumbsUp(User user) {
        return jpaQueryFactory.select(Projections.constructor(ThumbsUpResponseDto.class, docs))
                .from(thumbsUp)
                .leftJoin(docs)
                .on(thumbsUp.user.eq(user))
                .distinct()
                .fetch();
    }
}
