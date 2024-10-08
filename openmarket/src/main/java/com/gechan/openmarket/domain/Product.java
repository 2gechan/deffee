package com.gechan.openmarket.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString(exclude = "productImageList")
@Table(name = "tbl_product")
@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pno = null;

    private String pname;

    private int price;

    private String pdesc;

    private boolean sellFlag;

    private LocalDate uploadDate;

    @Builder.Default
    @ElementCollection
    private List<ProductImage> productImageList = new ArrayList<>();

    public void changePname(String pname) {
        this.pname = pname;
    }

    public void changePrice(int price) {
        this.price = price;
    }

    public void changePdesc(String pdesc) {
        this.pdesc = pdesc;
    }

    public void changeSellFlag(boolean sellFlag) {
        this.sellFlag = sellFlag;
    }

    public void addImage(ProductImage image) {
        image.setOrder(productImageList.size());
        productImageList.add(image);
    }

    public void addImageString(String fileName) {
        ProductImage productImage = ProductImage.builder().fileName(fileName).build();

        addImage(productImage);
    }

    public void clearList() {
        productImageList.clear();
    }

    public void setUploadDate(LocalDate date) {
        this.uploadDate = date;
    }

}
