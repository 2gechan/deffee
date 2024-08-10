package com.gechan.openmarket.repository;

import com.gechan.openmarket.domain.Product;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.UUID;

@SpringBootTest
@Log4j2
public class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    @Test
    void insertTest() {
        for (int i = 1; i <= 100; i++) {
            Product product = Product.builder()
                    .pname("TEST.." + i).price(1000 + i).pdesc("TEST DESC.." + i)
                    .uploadDate(LocalDate.of(2024, 8, 10)).build();
            product.addImageString(UUID.randomUUID()+"_"+"testImage1.jpg");
            product.addImageString(UUID.randomUUID()+"_"+"testImage2.jpg");

            productRepository.save(product);
        }
    }

    @Test
    void pagingTest() {

        Pageable pageable = PageRequest.of(0, 10, Sort.by("pno").descending());
        Page<Object[]> result = productRepository.selectList(pageable);

        log.debug("토탈 카운트 : {}", result.getTotalElements());
        result.getContent().forEach(arr -> log.debug(Arrays.toString(arr)));

    }
}
