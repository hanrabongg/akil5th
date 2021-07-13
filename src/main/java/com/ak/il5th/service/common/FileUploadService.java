package com.ak.il5th.service.common;

import com.ak.il5th.domain.FileUpload;
import com.ak.il5th.domain.FileUploadRepository;
import com.ak.il5th.web.dto.FileListResponseDto;
import com.ak.il5th.web.dto.FileUploadRequestDto;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class FileUploadService {

    private final ModelMapper modelMapper = new ModelMapper();

    private final FileUploadRepository fileUploadRepository;

    @Transactional
    public Long save(FileUploadRequestDto fileUploadRequestDto) {
        return fileUploadRepository.save(fileUploadRequestDto.toEntiry()).getId();
    }

//    @Transactional(readOnly = true)
//    public List<FileUploadRequestDto> findAllData() {
//        return fileUploadRepository.findAll(Sort.by(Sort.Direction.DESC, "CREATED_DATE")).stream()
//                .map(fileUpload -> modelMapper.map(fileUpload, FileUploadRequestDto.class)).collect(Collectors.toList());
//    }

    @Transactional(readOnly = true)
    public List<FileListResponseDto> findFileListByProjectId(String projectId) {
        return fileUploadRepository.findFileUploadByProjectIdOrderByCreatedDate(projectId).stream()
                .map(FileListResponseDto::new).collect(Collectors.toList());
    }

}
