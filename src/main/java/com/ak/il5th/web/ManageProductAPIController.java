package com.ak.il5th.web;

import com.ak.il5th.service.code.ProductCodeService;
import com.ak.il5th.web.dto.ProductKindSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class ManageProductAPIController {

    private final ProductCodeService productCodeService;

    @PostMapping("/api/v1/productKindSave")
    public Long productKindSave(@RequestBody ProductKindSaveRequestDto requestDto) {
        return productCodeService.save(requestDto);
    }
}
