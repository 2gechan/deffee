package com.gechan.product_management.service;

import com.gechan.product_management.dto.ProductDTO;
import com.gechan.product_management.exception.EntityNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("prod")
class SimpleProductServiceTest {

    @Autowired
    SimpleProductService simpleProductService;

    @Transactional
    @Test
    @DisplayName("상품을 추가한 후 id로 조회하면 해당 상품이 조회되어야 한다.")
    public void productAddFindByIdTest() throws Exception {
        ProductDTO productDTO = new ProductDTO("연필", 300, 20);

        ProductDTO savedProductDTO = simpleProductService.add(productDTO);
        Long savedProductId = savedProductDTO.getId();

        ProductDTO foundProductDTO = simpleProductService.findById(savedProductId);

//        System.out.println(savedProductDTO.getId() == foundProductDTO.getId());
//        System.out.println(savedProductDTO.getName() == foundProductDTO.getName());
//        System.out.println(savedProductDTO.getPrice() == foundProductDTO.getPrice());
//        System.out.println(savedProductDTO.getAmount() == foundProductDTO.getAmount());

        assertTrue(savedProductDTO.getId().equals(foundProductDTO.getId()));
        assertTrue(savedProductDTO.getName().equals(foundProductDTO.getName()));
        assertTrue(savedProductDTO.getPrice().equals(foundProductDTO.getPrice()));
        assertTrue(savedProductDTO.getAmount().equals(foundProductDTO.getAmount()));
    }

    @Test
    @DisplayName("존재하지 않는 상품 id로 조회하면 EntityNotFoundException 이 발생해야한다.")
    public void findProductNotExistIdTest() throws Exception {
        // given
        Long notExistId = -1L;

        // when
        assertThrows(EntityNotFoundException.class, () -> {
            simpleProductService.findById(notExistId);
        });

        // then

    }
}