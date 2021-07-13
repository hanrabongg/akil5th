package com.ak.il5th.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class FileUpload extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String projectId;

    @Column(nullable = false)
    private String originFileName;

    @Column(nullable = false)
    private String realFileName;

    @Column(nullable = false)
    private String filePath;

    @Column()
    private String format;

    @Column()
    private String createdUser;

    @Column()
    private String teamName;

    @Builder
    public FileUpload(Long id, String projectId, String originFileName, String realFileName, String filePath, String format, String createdUser, String teamName) {
        this.id = id;
        this.projectId = projectId;
        this.originFileName = originFileName;
        this.realFileName = realFileName;
        this.filePath = filePath;
        this.format = format;
        this.createdUser = createdUser;
        this.teamName = teamName;
    }
}
