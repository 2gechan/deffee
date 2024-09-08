package com.gechan.product_management.service;

import com.gechan.product_management.domain.Product;
import com.gechan.product_management.dto.ProductDTO;
import com.gechan.product_management.repository.ListProductRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class SimpleProductService {

    private final ListProductRepository listProductRepository;
    private final ModelMapper modelMapper;

    public SimpleProductService(ListProductRepository listProductRepository, ModelMapper modelMapper) {
        this.listProductRepository = listProductRepository;
        this.modelMapper = modelMapper;
    }

    public ProductDTO add(ProductDTO productDTO) {
        // ProductDTO -> Product 변환
        Product product = modelMapper.map(productDTO, Product.class);

        // Repository 호출
        Product saveProduct = listProductRepository.add(product);

        // Product -> ProductDTO 변환
        ProductDTO saveProductDTO = modelMapper.map(saveProduct, ProductDTO.class);

         return saveProductDTO;
    }
}
