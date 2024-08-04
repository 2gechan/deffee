package com.gechan.openmarket.service.impl;

import com.gechan.openmarket.domain.Product;
import com.gechan.openmarket.domain.ProductImage;
import com.gechan.openmarket.dto.ProductDTO;
import com.gechan.openmarket.repository.ProductRepository;
import com.gechan.openmarket.service.ProductService;
import lombok.Builder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    private Product dtoToEntity(ProductDTO productDTO) {
        Product product = Product.builder()
                .pname(productDTO.getPname())
                .price(productDTO.getPrice())
                .pdesc(productDTO.getPdesc())
                .build();

        List<String> uploadFileNames = productDTO.getUploadFileNames();
        uploadFileNames.forEach(fileName -> product.addImageString(fileName));

        return product;
    }

    private ProductDTO entityToDTO(Product product) {
        ProductDTO productDTO = ProductDTO.builder()
                .pno(product.getPno())
                .pname(product.getPname())
                .price(product.getPrice())
                .pdesc(product.getPdesc())
                .uploadDate(product.getUploadDate())
                .sellFlag(product.isSellFlag())
                .build();

        List<ProductImage> imageList = product.getProductImageList();
        if (imageList == null || imageList.isEmpty()) {
            return productDTO;
        }

        List<String> imageNamesList = imageList.stream()
                .map(image -> image.getFileName()).toList();

        productDTO.setUploadFileNames(imageNamesList);

        return productDTO;
    }

    @Override
    public Long register(ProductDTO productDTO) {
        Product product = dtoToEntity(productDTO);

        LocalDate date = LocalDate.now();
        product.setUploadDate(date);

        Long pno = productRepository.save(product).getPno();
        return pno;
    }

    @Override
    public List<Product> getList() {
        return null;
    }

    @Override
    public ProductDTO getOne(Long pno) {

        Optional<Product> findProduct = productRepository.findById(pno);
        Product product = findProduct.orElseThrow();

        return entityToDTO(product);
    }

    @Override
    public Map<String, String> update(ProductDTO productDTO) {
        return null;
    }

    @Override
    public Map<String, String> delete(Long pno) {
        return null;
    }
}
