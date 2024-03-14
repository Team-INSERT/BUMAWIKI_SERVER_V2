package com.project.bumawiki.domain.docs.domain;

import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.CreatedDate;

import com.project.bumawiki.domain.contribute.domain.Contribute;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class VersionDocs {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "version_docs_id")
	private Long id;

	@NotNull
	private Long docsId;

	@Column(columnDefinition = "TEXT")
	@NotNull
	private String contents;

	@CreatedDate
	private LocalDateTime thisVersionCreatedAt;

	@OneToOne(mappedBy = "versionDocs", cascade = CascadeType.ALL)
	private Contribute contributor;

	public void updateContributor(Contribute contribute) {
		this.contributor = contribute;
	}
}
