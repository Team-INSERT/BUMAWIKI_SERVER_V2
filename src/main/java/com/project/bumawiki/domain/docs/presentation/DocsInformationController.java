package com.project.bumawiki.domain.docs.presentation;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.bumawiki.domain.docs.domain.type.DocsType;
import com.project.bumawiki.domain.docs.exception.DocsTypeNotFoundException;
import com.project.bumawiki.domain.docs.presentation.dto.ClubResponseDto;
import com.project.bumawiki.domain.docs.presentation.dto.DocsTypeDto;
import com.project.bumawiki.domain.docs.presentation.dto.TeacherResponseDto;
import com.project.bumawiki.domain.docs.presentation.dto.response.DocsNameAndEnrollResponseDto;
import com.project.bumawiki.domain.docs.presentation.dto.response.DocsResponseDto;
import com.project.bumawiki.domain.docs.presentation.dto.response.DocsThumbsUpResponseDto;
import com.project.bumawiki.domain.docs.presentation.dto.response.VersionDocsDiffResponseDto;
import com.project.bumawiki.domain.docs.presentation.dto.response.VersionResponseDto;
import com.project.bumawiki.domain.docs.service.DocsInformationService;

import lombok.RequiredArgsConstructor;

@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/docs")
public class DocsInformationController {
	private final DocsInformationService docsInformationService;

	@GetMapping("/all/teacher")
	public ResponseEntity<TeacherResponseDto> findAllTeacher() {
		return ResponseEntity.ok(docsInformationService.getAllTeacher());
	}

	@GetMapping("/all/club")
	public ResponseEntity<ClubResponseDto> findAllClub() {
		return ResponseEntity.ok(docsInformationService.getAllClub());
	}

	@GetMapping("/{stringDocsType}")
	public ResponseEntity<DocsTypeDto> findAllByDocsType(
		@PathVariable String stringDocsType) {

		DocsType docsType = DocsType.valueOfLabel(stringDocsType);
		if (docsType == null) {
			System.out.println();
			System.out.println("Not found Docs type : " + stringDocsType);
			throw DocsTypeNotFoundException.EXCEPTION;
		}

		return ResponseEntity.ok(DocsTypeDto.from(docsInformationService.findByDocsTypeOrderByEnroll(docsType)));
	}

	@GetMapping("/find/all/title/{title}")
	public ResponseEntity<List<DocsNameAndEnrollResponseDto>> findAllByTitle(@PathVariable String title) {
		return ResponseEntity.ok(docsInformationService.findAllByTitle(title));
	}

	@GetMapping("/find/title/{title}")
	public ResponseEntity<DocsResponseDto> findById(@PathVariable String title) {
		return ResponseEntity.ok(docsInformationService.findDocs(title));
	}

	@GetMapping("/find/{title}/version")
	public ResponseEntity<VersionResponseDto> showDocsVersion(@PathVariable String title) {
		return ResponseEntity.ok(docsInformationService.findDocsVersion(title));
	}

	@GetMapping("/find/modified")
	public ResponseEntity<List<DocsNameAndEnrollResponseDto>> showDocsModifiedTimeDesc(
		@PageableDefault(size = 12) Pageable pageable) {
		return ResponseEntity.ok(docsInformationService.showDocsModifiedAtDesc(pageable));
	}

	@GetMapping("/find/version/{title}/different/{version}")
	public ResponseEntity<VersionDocsDiffResponseDto> showVersionDocsDiff(@PathVariable String title,
		@PathVariable Long version) {
		return ResponseEntity.ok(docsInformationService.showVersionDocsDiff(title, version));
	}

	@GetMapping("/find/modified/all")
	public ResponseEntity<List<DocsNameAndEnrollResponseDto>> showDocsModifiedTimeDescAll() {
		return ResponseEntity.ok(docsInformationService.showDocsModifiedAtDescAll());
	}

	@GetMapping("/thumbs/up/get/{title}")
	public ResponseEntity<DocsThumbsUpResponseDto> getDocsThumbsUpsCount(@PathVariable String title) {
		return ResponseEntity.ok(docsInformationService.getDocsThumbsUpsCount(title));
	}
}
