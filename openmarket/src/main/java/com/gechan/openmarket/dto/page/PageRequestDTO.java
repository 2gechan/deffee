package com.gechan.openmarket.dto.page;

import lombok.Data;

@Data
public class PageRequestDTO {

    private int page = 1;
    private int size = 10;
}
