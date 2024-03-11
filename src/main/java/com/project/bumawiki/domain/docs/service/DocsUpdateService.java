package com.project.bumawiki.domain.docs.service;

import java.io.IOException;
import java.time.LocalDateTime;

import org.jetbrains.annotations.NotNull;
import org.springframework.transaction.annotation.Transactional;

import com.project.bumawiki.domain.contribute.domain.Contribute;
import com.project.bumawiki.domain.contribute.service.ContributeService;
import com.project.bumawiki.domain.docs.domain.Docs;
import com.project.bumawiki.domain.docs.domain.VersionDocs;
import com.project.bumawiki.domain.docs.domain.repository.DocsRepository;
import com.project.bumawiki.domain.docs.domain.repository.DocsRepositoryMapper;
import com.project.bumawiki.domain.docs.domain.repository.VersionDocsRepository;
import com.project.bumawiki.domain.docs.domain.type.DocsType;
import com.project.bumawiki.domain.docs.exception.CannotChangeYourDocsException;
import com.project.bumawiki.domain.docs.exception.NoUpdatableDocsException;
import com.project.bumawiki.domain.docs.presentation.dto.request.DocsTitleUpdateRequestDto;
import com.project.bumawiki.domain.docs.presentation.dto.request.DocsTypeUpdateDto;
import com.project.bumawiki.domain.docs.presentation.dto.request.DocsUpdateRequestDto;
import com.project.bumawiki.domain.docs.presentation.dto.response.DocsResponseDto;
import com.project.bumawiki.domain.user.UserFacade;
import com.project.bumawiki.domain.user.domain.User;
import com.project.bumawiki.domain.user.service.UserService;
import com.project.bumawiki.global.annotation.ServiceWithTransactionalReadOnly;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@ServiceWithTransactionalReadOnly
public class DocsUpdateService {
	private final DocsRepositoryMapper docsRepositoryMapper;
	private final DocsRepository docsRepository;
	private final VersionDocsRepository versionDocsRepository;
	private final ContributeService contributeService;
	private final UserService userService;
	private final UserFacade userFacade;

	@Transactional
	public DocsResponseDto execute(String bearer, String title, DocsUpdateRequestDto docsUpdateRequestDto) throws
		IOException {
		String authId = userService.checkIsLoginUser(bearer);
		Docs foundDocs = findDocsByTitle(title);
		validate(authId, foundDocs);

		VersionDocs savedVersionDocs = saveVersionDocs(docsUpdateRequestDto, foundDocs.getId());
		Docs docs = setVersionDocs(savedVersionDocs);

		setContribute(savedVersionDocs);

		return new DocsResponseDto(docs);
	}

	@Transactional
	public DocsResponseDto titleUpdate(String title, DocsTitleUpdateRequestDto requestDto) {

		docsRepositoryMapper.checkTitleAlreadyExist(requestDto.getTitle());

		Docs docs = findDocsByTitle(title);
		docs.updateTitle(requestDto.getTitle());

		return new DocsResponseDto(docs);
	}

	@Transactional
	public DocsResponseDto DocsTypeUpdate(final DocsTypeUpdateDto docsTypeUpdateDto) {
		Docs docs = docsRepository.findById(docsTypeUpdateDto.getId())
			.orElseThrow(() -> NoUpdatableDocsException.EXCEPTION);

		docs.updateDocsType(docsTypeUpdateDto.getDocsType());
		return new DocsResponseDto(docs);
	}

	@NotNull
	private Docs setVersionDocs(VersionDocs savedVersionDocs) {
		Docs docs = setVersionDocsToDocs(savedVersionDocs);
		docs.setModifiedTime(savedVersionDocs.getThisVersionCreatedAt());
		return docs;
	}

	private void setContribute(VersionDocs savedVersionDocs) {
		Contribute contribute = contributeService.updateContribute(savedVersionDocs);
		savedVersionDocs.updateContributor(contribute);
	}

	private Docs findDocsByTitle(String title) {
		return docsRepository.findByTitle(title)
			.orElseThrow(() -> NoUpdatableDocsException.EXCEPTION);
	}

	private void validate(String authId, Docs foundDocs) {
		updateDocsOneself(foundDocs.getTitle(), foundDocs.getEnroll(), authId, foundDocs.getDocsType());
		updateReadOnlyDocs(foundDocs.getDocsType());
	}

	private VersionDocs saveVersionDocs(DocsUpdateRequestDto docsUpdateRequestDto, Long docsId) {
		return versionDocsRepository.save(
			VersionDocs.builder()
				.docsId(docsId)
				.thisVersionCreatedAt(LocalDateTime.now())
				.contents(docsUpdateRequestDto.getContents())
				.build()
		);
	}

	private void updateDocsOneself(String title, Integer enroll, String authId, DocsType docsType) {
		User user = userFacade.getUserByEmail(authId);

		if (docsType.equals(DocsType.STUDENT)) {
			if (title.contains(user.getName()) && enroll.equals(user.getEnroll())) {
				throw CannotChangeYourDocsException.EXCEPTION;
			}
			return;
		}

		if (title.contains(user.getName())) {
			throw CannotChangeYourDocsException.EXCEPTION;
		}
	}

	private void updateReadOnlyDocs(DocsType docsType) {

		if (docsType.equals(DocsType.READONLY))
			throw NoUpdatableDocsException.EXCEPTION;
	}

	private Docs setVersionDocsToDocs(VersionDocs versionDocs) {
		Docs docs = docsRepository.findById(versionDocs.getDocsId())
			.orElseThrow(() -> NoUpdatableDocsException.EXCEPTION);

		docs.getDocsVersion().add(versionDocs);

		return docs;
	}
}

