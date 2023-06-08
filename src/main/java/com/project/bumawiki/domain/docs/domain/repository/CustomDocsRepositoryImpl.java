package com.project.bumawiki.domain.docs.domain.repository;

import com.project.bumawiki.domain.docs.domain.Docs;
import com.project.bumawiki.domain.docs.presentation.dto.response.VersionDocsResponseDto;
import com.project.bumawiki.domain.docs.presentation.dto.response.VersionResponseDto;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.project.bumawiki.domain.contribute.domain.QContribute.contribute;
import static com.project.bumawiki.domain.docs.domain.QDocs.docs;
import static com.project.bumawiki.domain.docs.domain.QVersionDocs.versionDocs;
import static com.project.bumawiki.domain.user.entity.QUser.user;

@RequiredArgsConstructor
@Repository
public class CustomDocsRepositoryImpl implements CustomDocsRepository {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public VersionResponseDto getDocsVersion(Docs findDocs) {
        List<VersionDocsResponseDto> versionDocsResponseDto = jpaQueryFactory
                .select(constructor(VersionDocsResponseDto.class, versionDocs.thisVersionCreatedAt, user.id, user.nickName))
                .from(docs)
                .join(docs.docsVersion, versionDocs)
                .join(versionDocs.contributor, contribute)
                .join(contribute.contributor, user)
                .where(docs.id.eq(findDocs.getId()))
                .distinct()
                .orderBy(versionDocs.thisVersionCreatedAt.desc())
                .fetch();

        return new VersionResponseDto(versionDocsResponseDto);
    }
}
