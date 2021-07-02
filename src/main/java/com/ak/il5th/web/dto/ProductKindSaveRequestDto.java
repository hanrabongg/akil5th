package com.ak.il5th.web.dto;

import com.ak.il5th.domain.code.ProductKind;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ProductKindSaveRequestDto {

    private String productType;
    private String productName;
    private Integer productOrder;

    @Builder
    public ProductKindSaveRequestDto(String productType, String productName, Integer productOrder) {
        this.productType = productType;
        this.productName = productName;
        this.productOrder = productOrder;
    }

    public ProductKind toEntity() {
        return ProductKind.builder()
                .productType(productType)
                .productName(productName)
                .productOrder(productOrder)
                .build();
    }

}
