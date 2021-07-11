package com.ak.il5th.web;

import com.ak.il5th.service.pdf.ConnectVisionAPI;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
public class IndexController {

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

    @GetMapping("/pdfRead_pdfBox")
    public String pdfReadByPDFBox() {
        return "pdf-pdfBox";
    }




    @GetMapping("/projectList")
    public String projectList() {
        return "page/projectList";
    }

    @GetMapping("/projectDetail")
    public String projectDetail(@RequestParam("id") String id) {
        System.out.println("projectID : " + id);
        return "page/projectDetail";
    }





    @GetMapping("/marketingDraft")
    public String marketingNew(@RequestParam("projectId") String id, @RequestParam("requestType") String requestType) {
        System.out.println("projectID : " + id +"/requestType : "+requestType);

        if ("NEW".equals(requestType)) {
            System.out.println("신규!");
        } else {
            System.out.println("수정!");
        }

        return "page/marketingDraft";
    }

    @GetMapping("/labDraft")
    public String labDraft(@RequestParam("projectId") String id, @RequestParam("requestType") String requestType) {
        System.out.println("projectID : " + id +"/requestType : "+requestType);
        return "page/laboratoryDraft";
    }




//    pdfRead_pdfBox
//            pdfRead_api
//    typoCheck


}
