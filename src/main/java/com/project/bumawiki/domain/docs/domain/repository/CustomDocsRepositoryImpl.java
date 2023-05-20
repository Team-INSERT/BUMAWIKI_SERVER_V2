package com.project.bumawiki.domain.docs.domain.repository;

import com.project.bumawiki.domain.contribute.domain.QContribute;
import com.project.bumawiki.domain.docs.domain.Docs;
import com.project.bumawiki.domain.docs.presentation.dto.response.VersionDocsResponseDto;
import com.project.bumawiki.domain.docs.presentation.dto.response.VersionResponseDto;
import com.project.bumawiki.domain.user.entity.QUser;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.project.bumawiki.domain.contribute.domain.QContribute.*;
import static com.project.bumawiki.domain.docs.domain.QDocs.docs;
import static com.project.bumawiki.domain.docs.domain.QVersionDocs.versionDocs;
import static com.project.bumawiki.domain.user.entity.QUser.*;
import static com.querydsl.core.types.Projections.constructor;

@RequiredArgsConstructor
@Repository
public class CustomDocsRepositoryImpl implements CustomDocsRepository {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public VersionResponseDto getDocsVersion(Docs findDocs) {
        List<VersionDocsResponseDto> versionDocsResponseDto = jpaQueryFactory
                .select(constructor(VersionDocsResponseDto.class, versionDocs, versionDocs.contributor.contributor))
                .from(docs)
                .leftJoin(docs.docsVersion, versionDocs)
//                .leftJoin(versionDocs, contribute.versionDocs)
//                .leftJoin(contribute.contributor, user)
                .where(versionDocs.docsId.eq(findDocs.getId()))
                .distinct()
                .orderBy(versionDocs.thisVersionCreatedAt.asc())
                .fetch();

        return new VersionResponseDto(versionDocsResponseDto);
    }
}
