package com.gechan.product_management.service;

import com.gechan.product_management.domain.Product;
import com.gechan.product_management.repository.ListProductRepository;
import org.springframework.stereotype.Service;

@Service
public class SimpleProductService {

    private final ListProductRepository listProductRepository;

    public SimpleProductService(ListProductRepository listProductRepository) {
        this.listProductRepository = listProductRepository;
    }

    public Product add(Product product) {
        Product saveProduct = listProductRepository.add(product);

        return saveProduct;
    }
}
