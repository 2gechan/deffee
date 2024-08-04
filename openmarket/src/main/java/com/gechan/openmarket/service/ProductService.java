package com.gechan.openmarket.service;

import com.gechan.openmarket.domain.Product;
import com.gechan.openmarket.dto.ProductDTO;

import java.util.List;
import java.util.Map;

public interface ProductService {

    public Long register(ProductDTO productDTO);

    public List<Product> getList();

    public ProductDTO getOne(Long pno);

    public Map<String, String> update(ProductDTO productDTO);

    public Map<String, String> delete(Long pno);

}
