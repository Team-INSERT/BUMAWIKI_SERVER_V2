package com.project.bumawiki.domain.docs.domain;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Clob;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class VersionDocs {

    @Id @GeneratedValue
    @Column(name = "versionDocs_id")
    private Long id;

    @NotNull
    private Long docsId;

    @Column(length = 32)
    @NotNull
    private String title;

    @Column(columnDefinition = "TEXT")
    @NotNull
    private String contents;

    @Lob
    private List<Clob> imageLink = new ArrayList<>();

    @CreatedDate
    private LocalDateTime thisVersionCreatedAt;
}
