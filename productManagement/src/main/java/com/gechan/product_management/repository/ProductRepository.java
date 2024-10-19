package com.gechan.product_management.repository;

import com.gechan.product_management.domain.Product;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.util.List;

public interface ProductRepository {
    Product add(Product product);

    Product findById(Long id);

    List<Product> findAll();

    List<Product> findByNameContaining(String name);

    Product update(Product product);

    void delete(Long id);
}
