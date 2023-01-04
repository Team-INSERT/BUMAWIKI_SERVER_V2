package com.project.bumawiki.domain.docs.domain;

import com.project.bumawiki.domain.contribute.domain.Contribute;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

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

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "versionDocs_id")
    private Long id;

    @NotNull
    private Long docsId;

    @Column(columnDefinition = "TEXT")
    @NotNull
    private String contents;

    @CreatedDate
    private LocalDateTime thisVersionCreatedAt;

    @OneToMany(mappedBy = "versionDocs", cascade = CascadeType.ALL)
    private List<Contribute> contributor = new ArrayList<>();
}
