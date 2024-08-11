package com.gechan.openmarket.service;

import com.gechan.openmarket.domain.Product;
import com.gechan.openmarket.dto.ProductDTO;
import com.gechan.openmarket.dto.page.PageRequestDTO;
import com.gechan.openmarket.dto.page.PageResponseDTO;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Transactional
public interface ProductService {

    public Long register(ProductDTO productDTO);

    public PageResponseDTO<ProductDTO> getList(PageRequestDTO pageRequestDTO);

    public ProductDTO getOne(Long pno);

    public Long update(ProductDTO productDTO);

    public Map<String, String> delete(Long pno);

}
