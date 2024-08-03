package com.gechan.openmarket.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "productImageList")
@Table(name = "tbl_product")
@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pno;

    private String pname;

    private int price;

    private String pdesc;

    private boolean sellFlag;

    private LocalDate uploadDate;

    @Builder.Default
    @ElementCollection
    private List<ProductImage> productImageList = new ArrayList<>();

    public void changeSellFlag(boolean sellFlag) {
        this.sellFlag = sellFlag;
    }

    public void addImage(ProductImage image) {
        image.setOrder(productImageList.size());
        productImageList.add(image);
    }

}
