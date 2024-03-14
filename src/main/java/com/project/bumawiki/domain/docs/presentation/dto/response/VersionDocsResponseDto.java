package com.project.bumawiki.domain.docs.presentation.dto.response;

import java.time.LocalDateTime;

import lombok.Getter;

public record VersionDocsResponseDto(Long id, LocalDateTime thisVersionCreatedAt, Long userId, String nickName) {
}
