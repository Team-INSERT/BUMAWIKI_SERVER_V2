package com.project.bumawiki.global.s3.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.project.bumawiki.global.s3.controller.dto.ImageResponse;
import com.project.bumawiki.global.s3.service.CommandImageService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/s3")
public class S3Controller {
	private final CommandImageService commandImageService;

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ImageResponse upload(@RequestPart("file") MultipartFile file) {
		return ImageResponse.from(
			commandImageService.uploadImage(file)
		);
	}
}
