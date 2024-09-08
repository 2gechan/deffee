package com.gechan.product_management.controller;

import com.gechan.product_management.domain.Product;
import com.gechan.product_management.dto.ProductDTO;
import com.gechan.product_management.service.SimpleProductService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {

    private final SimpleProductService simpleProductService;

    public ProductController(SimpleProductService simpleProductService) {
        this.simpleProductService = simpleProductService;
    }

    @RequestMapping(value = "/products", method = RequestMethod.POST)
    public ProductDTO createProduct(@RequestBody ProductDTO productDTO) {

        return simpleProductService.add(productDTO);
    }
}
