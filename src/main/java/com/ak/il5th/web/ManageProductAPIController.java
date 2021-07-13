package com.ak.il5th.web;

import com.ak.il5th.service.code.ProductCodeService;
import com.ak.il5th.service.common.FileUploadService;
import com.ak.il5th.service.pdf.ConnectVisionAPI;
import com.ak.il5th.service.pdf.TikaPDFParser;
import com.ak.il5th.web.dto.FileUploadRequestDto;
import com.ak.il5th.web.dto.ProductKindSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.imgscalr.Scalr;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

@RequiredArgsConstructor
@RestController
public class ManageProductAPIController {

    private final ProductCodeService productCodeService;
    private final FileUploadService fileUploadService;
    private final ConnectVisionAPI connectVisionAPI;
    private final TikaPDFParser tikaPDFParser;

    @Value("${spring.servlet.multipart.location}")
    String uploadPath;

    @PostMapping("/api/v1/productKindSave")
    public Long productKindSave(@RequestBody ProductKindSaveRequestDto requestDto) {
        return productCodeService.save(requestDto);
    }

    @ResponseBody
    @RequestMapping(value = "/api/v1/getTextViaAPI")
    public Map<String, String> getTextViaAPI(@RequestBody Map<String, String> param) {
        String path = "";
        String detectedText = "";
        if (param.containsKey("fileUrl")) {
            path = param.get("fileUrl").toString();
        }
        path = "C:\\workspace_springboot\\akil5th\\src\\main\\resources\\static\\dist\\file\\1.루나에센스수분광팩트CX_디자인.jpg";

        detectedText = connectVisionAPI.getTextFromImgViaVisionAPI(path);

        Map<String, String> resultMap = new HashMap<String, String>();
        resultMap.put("detectedText", detectedText);

        System.out.println("Map : " + resultMap.toString());
        return resultMap;
    }

    @ResponseBody
    @RequestMapping(value = "/api/v1/getTextViaTika")
    public Map<String, String> getTextViaTika(@RequestBody Map<String, String> param) {
        String path = "";
        String detectedText = "";
        if (param.containsKey("fileUrl")) {
            path = param.get("fileUrl").toString();
        }
        path = "C:\\workspace_springboot\\akil5th\\src\\main\\resources\\static\\dist\\file\\[1167673]pouch19리큐진한겔알카2L리필(드).pdf";

        detectedText = tikaPDFParser.getTextFromPdfViaTika(path);

        Map<String, String> resultMap = new HashMap<String, String>();
        resultMap.put("detectedText", detectedText);

        System.out.println("Map : " + resultMap.toString());
        return resultMap;
    }

    @ResponseBody
    @RequestMapping(value ="/api/v1/uploadFiles", method=RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public void file_uploads(MultipartFile nfile, MultipartHttpServletRequest request)throws Exception{

        String projectId = request.getParameter("projectId");
        Map <String, MultipartFile > paramMap = request.getFileMap();
        Iterator keyData = paramMap.keySet().iterator();

        while (keyData.hasNext()) {
            FileUploadRequestDto fileUploadRequestDto = new FileUploadRequestDto();
            Map<String, String> data = new HashMap<>();

            String key = ((String)keyData.next());
            MultipartFile file = paramMap.get(key);

            // 물리적 저장
            String originName = file.getOriginalFilename().replaceAll(" ", "").replaceAll("'", "");
            String realName = UUID.randomUUID().toString() + originName;
            String formatName = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".")+1);
            Path dest = Paths.get(uploadPath+"/"+realName);
            file.transferTo(dest);

            // DB 저장
            fileUploadRequestDto = FileUploadRequestDto.builder().projectId(projectId).originFileName(originName).realFileName(realName).filePath("/upload/"+realName).format(formatName).createdUser("박자인").teamName("디자인팀").build();
            fileUploadService.save(fileUploadRequestDto);

        }
    }

}
