package com.project.bumawiki.domain.docs.domain;

import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import java.sql.Clob;
import java.time.ZonedDateTime;
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

    private Long DocsId;

    @Column(length = 32)
    private String title;

    @Column(columnDefinition = "TEXT")
    private String contents;

    @Lob
    private List<Clob> EncodedImage = new ArrayList<>();

    @Column(length = 8)
    private int enroll;

}
