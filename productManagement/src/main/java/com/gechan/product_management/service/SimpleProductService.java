package com.gechan.product_management.service;

import com.gechan.product_management.domain.Product;
import com.gechan.product_management.dto.ProductDTO;
import com.gechan.product_management.repository.ListProductRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public ProductDTO findById(Long id) {
        Product product = listProductRepository.findById(id);
        ProductDTO productDTO = modelMapper.map(product, ProductDTO.class);
        return productDTO;
    }

    public List<ProductDTO> findAll() {
        List<Product> products = listProductRepository.findAll();
        List<ProductDTO> productDTOS = products.stream()
                .map(product -> modelMapper.map(product, ProductDTO.class))
                .toList();

        return productDTOS;
    }

    public List<ProductDTO> findByNameContaining(String name) {
        List<Product> products = listProductRepository.findByNameContaining(name);
        List<ProductDTO> productDTOS = products.stream()
                .map(product -> modelMapper.map(product, ProductDTO.class))
                .toList();

        return productDTOS;
    }


    public ProductDTO update(ProductDTO productDTO) {
        Product product = modelMapper.map(productDTO, Product.class);
        Product updatedProduct = listProductRepository.update(product);

        ProductDTO updatedProductDTO = modelMapper.map(updatedProduct, ProductDTO.class);

        return updatedProductDTO;
    }

    public void delete(Long id) {
        listProductRepository.delete(id);
    }
}
