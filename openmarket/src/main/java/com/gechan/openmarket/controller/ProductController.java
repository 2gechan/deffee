package com.gechan.openmarket.controller;

import com.gechan.openmarket.dto.ProductDTO;
import com.gechan.openmarket.service.ProductService;
import com.gechan.openmarket.util.FileUtil;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/product")
public class ProductController {

    private final FileUtil fileUtil;
    private final ProductService productService;

    public ProductController(FileUtil fileUtil, ProductService productService) {
        this.fileUtil = fileUtil;
        this.productService = productService;
    }

    @PostMapping("/")
    public Map<String, Long> register(ProductDTO productDTO) {

        List<MultipartFile> files = productDTO.getFiles();
        List<String> uploadFileNames = fileUtil.saveFiles(files);

        productDTO.setUploadFileNames(uploadFileNames);

        Long pno = productService.register(productDTO);

        return Map.of("RESULT", pno);
    }

    @GetMapping("/{pno}")
    public ProductDTO getOne(@PathVariable Long pno) {
        return productService.getOne(pno);
    }
}
