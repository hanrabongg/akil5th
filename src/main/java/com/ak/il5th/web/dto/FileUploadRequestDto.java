package com.ak.il5th.web.dto;

import com.ak.il5th.domain.FileUpload;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@Getter
@NoArgsConstructor
public class FileUploadRequestDto {

    private String projectId;
    private String originFileName;
    private String realFileName;
    private String filePath;
    private String createdUser;
    private String teamName;
    private String format;
    private String createDate;

    @Builder
    public FileUploadRequestDto(String projectId, String originFileName, String realFileName, String filePath, String createdUser, String teamName, String format, String createDate) {
        this.projectId = projectId;
        this.originFileName = originFileName;
        this.realFileName = realFileName;
        this.filePath = filePath;
        this.createdUser = createdUser;
        this.teamName = teamName;
        this.format = format;
        this.createDate = createDate;
    }

    public FileUpload toEntiry() {
        return FileUpload.builder()
                .projectId(projectId)
                .originFileName(originFileName)
                .realFileName(realFileName)
                .filePath(filePath)
                .createdUser(createdUser)
                .teamName(teamName)
                .format(format)
                .build();
    }

}
