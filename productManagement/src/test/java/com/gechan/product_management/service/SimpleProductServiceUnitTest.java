package com.gechan.product_management.service;

import com.gechan.product_management.domain.Product;
import com.gechan.product_management.dto.ProductDTO;
import com.gechan.product_management.repository.ProductRepository;
import com.gechan.product_management.validation.ValidationService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class SimpleProductServiceUnitTest {

    @Mock
    private ProductRepository productRepository;

    @Mock
    private ValidationService validationService;

    @InjectMocks
    private SimpleProductService simpleProductService;

    @Test
    @DisplayName("상품 추가 후에는 추가된 상품이 반환되어야한다.")
    public void productAddTest() throws Exception {
        // given
        ProductDTO productDTO = new ProductDTO("연필", 300, 20);
        Long PRODUCT_ID = 1L;

        // when
        Product product = ProductDTO.toEntity(productDTO);
        product.setId(PRODUCT_ID);
        when(productRepository.add(any())).thenReturn(product);
        ProductDTO savedProductDTO = simpleProductService.add(productDTO);

        // then
        Assertions.assertTrue(savedProductDTO.getId().equals(PRODUCT_ID));
        Assertions.assertTrue(savedProductDTO.getName().equals(productDTO.getName()));
        Assertions.assertTrue(savedProductDTO.getPrice().equals(productDTO.getPrice()));
        Assertions.assertTrue(savedProductDTO.getAmount().equals(productDTO.getAmount()));

    }
}
