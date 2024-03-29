package com.project.bumawiki.global.config.querydsl;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import jakarta.persistence.EntityManager;

@Configuration
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class QueryDslConfig {

    private final EntityManager em;

    @Bean
    public JPAQueryFactory queryFactory() {
        return new JPAQueryFactory(em);
    }
}
