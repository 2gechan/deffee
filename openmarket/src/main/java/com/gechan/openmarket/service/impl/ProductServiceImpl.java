package com.gechan.openmarket.service.impl;

import com.gechan.openmarket.domain.Product;
import com.gechan.openmarket.domain.ProductImage;
import com.gechan.openmarket.dto.ProductDTO;
import com.gechan.openmarket.dto.page.PageRequestDTO;
import com.gechan.openmarket.dto.page.PageResponseDTO;
import com.gechan.openmarket.repository.ProductRepository;
import com.gechan.openmarket.service.ProductService;
import lombok.Builder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
    public PageResponseDTO<ProductDTO> getList(PageRequestDTO pageRequestDTO) {

        // springframework 에서 제공하는 Pageable 객체를 통해 요청 페이지, 사이즈(limit), 내림차순 정렬 설정
        Pageable pageable = PageRequest.of(pageRequestDTO.getPage() - 1, pageRequestDTO.getSize()
                , Sort.by("pno").descending());

        // Page 객체는 현재 페이지에 대한 데이터를 담고 그것과 별개로 테이블 전체 데이터의 개수, 전체 페이지 수, 현재 페이지 정보 등을 갖고있다.
        // object[] : 0 번째 product, 1 번째 productImage
        Page<Object[]> result = productRepository.selectList(pageable);

        List<ProductDTO> productDTOList = result.stream().map(arr -> {
            ProductDTO productDTO = null;

            Product product = (Product) arr[0];
            ProductImage productImage = (ProductImage) arr[1];

            productDTO = entityToDTO(product); // 상품 정보만 들어 있음

            String imageStr = productImage.getFileName();
            productDTO.setUploadFileNames(List.of(imageStr));

            return productDTO;
        }).toList();

        // 조회된 결과 총 개수
        long totalCount = result.getTotalElements();

        return PageResponseDTO.<ProductDTO>pageRes()
                .dtoList(productDTOList).pageRequestDTO(pageRequestDTO).totalCount((int) totalCount).build();

    }

    @Override
    public ProductDTO getOne(Long pno) {

        // Optional<Product> findProduct = productRepository.findById(pno);
        Optional<Product> findProduct = productRepository.selectOne(pno);
        Product product = findProduct.orElseThrow();

        return entityToDTO(product);
    }

    @Override
    public Long update(ProductDTO productDTO) {

        Optional<Product> oldProduct = productRepository.selectOne(productDTO.getPno());

        Product product = oldProduct.orElseThrow();

        product.changePname(productDTO.getPname());
        product.changePrice(productDTO.getPrice());
        product.changePdesc(productDTO.getPdesc());
        product.changeSellFlag(productDTO.isSellFlag());

        // 새로 변경된 파일 목록들
        List<String> uploadFileNames = productDTO.getUploadFileNames();

        product.clearList();

        if (uploadFileNames != null && !uploadFileNames.isEmpty()) {
            uploadFileNames.forEach(fileName -> {
                product.addImageString(fileName);
            });
        }

        return productRepository.save(product).getPno();

    }

    @Override
    public Map<String, String> delete(Long pno) {
        return null;
    }
}
