package com.project.bumawiki.domain.docs.presentation.dto;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.project.bumawiki.domain.docs.presentation.dto.response.DocsNameAndEnrollResponseDto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class DocsTypeDto {
	private Map<Integer, List<DocsNameAndEnrollResponseDto>> data;
	private Set<Integer> keys;

	public static DocsTypeDto from(Map<Integer, List<DocsNameAndEnrollResponseDto>> byEnroll) {
		return new DocsTypeDto(
			byEnroll,
			byEnroll.keySet()
		);
	}
}
