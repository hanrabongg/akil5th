package com.ak.il5th.web;

import com.ak.il5th.service.common.FileUploadService;
import com.ak.il5th.service.pdf.ConnectVisionAPI;
import com.ak.il5th.web.dto.FileListResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Controller
public class IndexController {

    private final FileUploadService fileUploadService;

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/productTypeSave")
    public String productTypeSave() {
        return "code/productType-save";
    }

    @GetMapping("/productSave")
    public String productSave() {
        return "product-save";
    }

//    @GetMapping("/pdfRead_pdfBox")
//    public String pdfReadByPDFBox() {
//        return "pdf-pdfBox";
//    }




    @GetMapping("/projectList")
    public String projectList() {
        return "page/projectList";
    }

    @GetMapping("/projectDetail")
    public ModelAndView projectDetail(@RequestParam("projectId") String id) {
        System.out.println("projectID : " + id);
        ModelAndView mv = new ModelAndView();

        List<FileListResponseDto> fileList = new ArrayList<>();
        fileList = fileUploadService.findFileListByProjectId(id);

        System.out.println("fileList.size() : " + fileList.size());

        mv.addObject("projectId", id);
        mv.addObject("fileList", fileList);

        mv.setViewName("page/projectDetail");

        return mv;

    }

//    @GetMapping("/getFileList")
//    public ModelAndView getFileList(@RequestParam("projectId") String id) {
//        System.out.println("projectID : " + id);
//        ModelAndView mv = new ModelAndView();
//
//        List<FileListResponseDto> fileList = new ArrayList<>();
//        fileList = fileUploadService.findFileListByProjectId(id);
//
//        System.out.println("fileList.size() : " + fileList.size());
//
//        mv.addObject("projectId", id);
//        mv.addObject("fileList", fileList);
//
//        mv.setViewName("page/projectDetail");
//
//        return mv;
//
//    }



    @GetMapping("/marketingDraft")
    public ModelAndView marketingNew(@RequestParam("projectId") String id, @RequestParam("requestType") String requestType) {
        System.out.println("projectID : " + id +"/requestType : "+requestType);
        ModelAndView mv = new ModelAndView();

        if ("NEW".equals(requestType)) {
            System.out.println("신규!");
        } else {
            System.out.println("수정!");
        }

        mv.addObject("projectId", id);

        mv.setViewName("page/marketingDraft");
        return mv;
    }

    @GetMapping("/labDraft")
    public String labDraft(@RequestParam("projectId") String id, @RequestParam("requestType") String requestType) {
        System.out.println("projectID : " + id +"/requestType : "+requestType);
        return "page/laboratoryDraft";
    }




}
