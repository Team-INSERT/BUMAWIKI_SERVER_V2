package com.project.bumawiki.domain.docs.domain;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;

import com.project.bumawiki.domain.contribute.domain.Contribute;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotNull;
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

	private int version;

	public void updateContributor(Contribute contribute) {
		this.contributor = contribute;
	}
}
