package com.gechan.openmarket.domain;

import jakarta.persistence.Embeddable;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
@ToString
@Embeddable
public class ProductImage {
    private String fileName;
    private int ord;

    public void setOrder(int ord) {
        this.ord = ord;
    }
}
