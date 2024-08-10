package com.gechan.openmarket.service;

import com.gechan.openmarket.dto.ProductDTO;
import com.gechan.openmarket.dto.page.PageRequestDTO;
import com.gechan.openmarket.dto.page.PageResponseDTO;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@Log4j2
public class ProductServiceTest {

    @Autowired
    private ProductService productService;

    @Test
    void productPagingTest() {
        PageRequestDTO pageRequestDTO = new PageRequestDTO();
        pageRequestDTO.setPage(1);
        pageRequestDTO.setSize(10);
        PageResponseDTO<ProductDTO> productList = productService.getList(pageRequestDTO);
        productList.getDtoList().stream().forEach(product -> {
            log.debug(product.toString());
        });
    }
}
