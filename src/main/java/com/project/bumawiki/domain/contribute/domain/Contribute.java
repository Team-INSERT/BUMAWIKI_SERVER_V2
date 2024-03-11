package com.project.bumawiki.domain.contribute.domain;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import org.springframework.data.annotation.CreatedDate;

import com.project.bumawiki.domain.docs.domain.Docs;
import com.project.bumawiki.domain.docs.domain.VersionDocs;
import com.project.bumawiki.domain.user.domain.User;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Contribute {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "contribute_id")
	private Long id;

	@CreatedDate
	private LocalDateTime createdAt;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private User contributor;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "docs_id")
	private Docs docs;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "version_docs_id")
	private VersionDocs versionDocs;
}
