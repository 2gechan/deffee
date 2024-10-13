package com.gechan.product_management.controller;

import com.gechan.product_management.domain.Product;
import com.gechan.product_management.dto.ProductDTO;
import com.gechan.product_management.service.SimpleProductService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {

    private final SimpleProductService simpleProductService;

    public ProductController(SimpleProductService simpleProductService) {
        this.simpleProductService = simpleProductService;
    }

    @RequestMapping(value = "/products", method = RequestMethod.POST)
    public ProductDTO createProduct(@Valid @RequestBody ProductDTO productDTO) {

        return simpleProductService.add(productDTO);
    }

    @RequestMapping(value = "/products/{id}", method = RequestMethod.GET)
    public ProductDTO findProductById(@PathVariable("id") Long id) {
        return simpleProductService.findById(id);
    }

//    @RequestMapping(value = "/products", method = RequestMethod.GET)
//    public List<ProductDTO> findAllProduct() {
//        return simpleProductService.findAll();
//    }

    @RequestMapping(value = "/products", method = RequestMethod.GET)
    public List<ProductDTO> findProducts(
            @RequestParam(required = false, name = "name") String name
    ) {
        if (name == null) {
            return simpleProductService.findAll();
        }

        return simpleProductService.findByNameContaining(name);
    }

    @RequestMapping(value = "/products/{id}", method = RequestMethod.PUT)
    public ProductDTO updateProduct(@PathVariable(name = "id") Long id, @RequestBody ProductDTO productDTO) {
        productDTO.setId(id);
        return simpleProductService.update(productDTO);
    }

    @RequestMapping(value = "/products/{id}", method = RequestMethod.DELETE)
    public void deleteProduct(@PathVariable(name = "id") Long id) {
        simpleProductService.delete(id);
    }
}
