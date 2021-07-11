package com.ak.il5th.web;

import com.ak.il5th.service.code.ProductCodeService;
import com.ak.il5th.service.pdf.ConnectVisionAPI;
import com.ak.il5th.service.pdf.TikaPDFParser;
import com.ak.il5th.web.dto.ProductKindSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@RestController
public class ManageProductAPIController {

    private final ProductCodeService productCodeService;
    private final ConnectVisionAPI connectVisionAPI;
    private final TikaPDFParser tikaPDFParser;

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
        path = "C:\\workspace_springboot\\akil5th\\src\\main\\resources\\static\\dist\\file\\1.루나에센스수분광팩트CX_디자인_2.pdf";

        detectedText = tikaPDFParser.getTextFromPdfViaTika(path);

        Map<String, String> resultMap = new HashMap<String, String>();
        resultMap.put("detectedText", detectedText);

        System.out.println("Map : " + resultMap.toString());
        return resultMap;
    }
}
