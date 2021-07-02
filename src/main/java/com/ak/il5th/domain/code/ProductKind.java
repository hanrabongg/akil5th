package com.ak.il5th.domain.code;

import com.ak.il5th.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class ProductKind extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 2, nullable = false)
    private String productType;

    @Column(length = 500, nullable = false)
    private String productName;

    @Column()
    @ColumnDefault("0") //default 0
    private Integer productOrder;


    @Builder
    public ProductKind(String productType, String productName, Integer productOrder) {
        this.productType = productType;
        this.productName = productName;
        this.productOrder = productOrder;
    }
}
