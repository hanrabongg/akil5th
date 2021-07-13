package com.ak.il5th.web.dto;

import com.ak.il5th.domain.FileUpload;
import lombok.Getter;

@Getter
public class FileListResponseDto {

    private String originFileName;
    private String realFileName;
    private String filePath;
    private String createdUser;
    private String teamName;
    private String format;
    private String createDate;

    public FileListResponseDto(FileUpload entity) {
        this.originFileName = entity.getOriginFileName();
        this.realFileName = entity.getRealFileName();
        this.filePath = entity.getFilePath();
        this.createdUser = entity.getCreatedUser();
        this.teamName = entity.getTeamName();
        this.format = entity.getFormat();
        this.createDate = entity.getCreatedDate().toString();
    }

}
