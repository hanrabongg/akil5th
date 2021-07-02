package com.ak.il5th.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

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


}
