package com.gechan.product_management.service;

import com.gechan.product_management.domain.Product;
import com.gechan.product_management.dto.ProductDTO;
import com.gechan.product_management.repository.DatabaseProductRepository;
import com.gechan.product_management.repository.ListProductRepository;
import com.gechan.product_management.repository.ProductRepository;
import com.gechan.product_management.validation.ValidationService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SimpleProductService {

    private final ProductRepository productRepository;
    // private final ModelMapper modelMapper;
    private final ValidationService validationService;

    public SimpleProductService(ProductRepository productRepository, ValidationService validationService) {
        this.productRepository = productRepository;
        // this.modelMapper = modelMapper;
        this.validationService = validationService;
    }

    public ProductDTO add(ProductDTO productDTO) {
        // ProductDTO -> Product 변환
        // Product product = modelMapper.map(productDTO, Product.class);
        Product product = ProductDTO.toEntity(productDTO);
        validationService.checkValid(product);

        // Repository 호출
        Product saveProduct = productRepository.add(product);

        // Product -> ProductDTO 변환
        // ProductDTO saveProductDTO = modelMapper.map(saveProduct, ProductDTO.class);
        ProductDTO saveProductDTO = ProductDTO.toDTO(saveProduct);

         return saveProductDTO;
    }

    public ProductDTO findById(Long id) {
        Product product = productRepository.findById(id);
        // ProductDTO productDTO = modelMapper.map(product, ProductDTO.class);
        ProductDTO productDTO = ProductDTO.toDTO(product);
        return productDTO;
    }

    public List<ProductDTO> findAll() {
        List<Product> products = productRepository.findAll();
        List<ProductDTO> productDTOS = products.stream()
                // .map(product -> modelMapper.map(product, ProductDTO.class))
                .map(product -> ProductDTO.toDTO(product))
                .toList();

        return productDTOS;
    }

    public List<ProductDTO> findByNameContaining(String name) {
        List<Product> products = productRepository.findByNameContaining(name);
        List<ProductDTO> productDTOS = products.stream()
                // .map(product -> modelMapper.map(product, ProductDTO.class))
                .map(product -> ProductDTO.toDTO(product))
                .toList();

        return productDTOS;
    }


    public ProductDTO update(ProductDTO productDTO) {
        // Product product = modelMapper.map(productDTO, Product.class);
        Product product = ProductDTO.toEntity(productDTO);
        Product updatedProduct = productRepository.update(product);

        // ProductDTO updatedProductDTO = modelMapper.map(updatedProduct, ProductDTO.class);
        ProductDTO updatedProductDTO = ProductDTO.toDTO(updatedProduct);

        return updatedProductDTO;
    }

    public void delete(Long id) {
        productRepository.delete(id);
    }
}
