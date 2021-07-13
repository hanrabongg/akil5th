package com.ak.il5th.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FileUploadRepository extends JpaRepository<FileUpload, Long>{

    List<FileUpload> findFileUploadByProjectIdOrderByCreatedDate(String projectId);
}
