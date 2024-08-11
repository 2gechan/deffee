package com.gechan.openmarket.controller;

import com.gechan.openmarket.dto.ProductDTO;
import com.gechan.openmarket.dto.page.PageRequestDTO;
import com.gechan.openmarket.dto.page.PageResponseDTO;
import com.gechan.openmarket.service.ProductService;
import com.gechan.openmarket.util.FileUtil;
import lombok.extern.log4j.Log4j2;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/product")
@Log4j2
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

    @GetMapping("/list")
    public PageResponseDTO<ProductDTO> getList(PageRequestDTO pageRequestDTO) {
        return productService.getList(pageRequestDTO);
    }

    @GetMapping("/view/{fileName}")
    public ResponseEntity<Resource> viewFile(@PathVariable("fileName") String fileName) {
        return fileUtil.getFile(fileName);
    }

    @GetMapping("/{pno}")
    public ProductDTO getOne(@PathVariable Long pno) {
        return productService.getOne(pno);
    }

    @PutMapping("/{pno}")
    public Map<String, Long> update(@PathVariable("pno") Long pno, ProductDTO productDTO) {

        // 기존에 저장 되어 있던 Product
        ProductDTO oldProductDTO = productService.getOne(pno);

        // 새로 추가된 파일 업로드
        List<MultipartFile> newImageFiles = productDTO.getFiles();
        List<String> newImageFileNames = fileUtil.saveFiles(newImageFiles);

        // 유지할 파일과 새로 업로드 된 파일명 리스트 합치기
        List<String> currentFileNames = productDTO.getUploadFileNames();
        if (currentFileNames != null && !currentFileNames.isEmpty()) {

            currentFileNames.addAll(newImageFileNames);
        }

        Long resultPno = productService.update(productDTO);

        List<String> oldFileNames = oldProductDTO.getUploadFileNames();
        if (oldFileNames != null && !oldFileNames.isEmpty()) {
            // 기존에 저장 되어 있던 파일명들 중 유지할 파일과 새로 업로드 된 파일들을 합친 리스트에 존재하지 않으면 추출
            List<String> removeFiles =
                    oldFileNames.stream().filter(fileName -> !newImageFileNames.contains(fileName)).toList();

            fileUtil.deleteFile(removeFiles);
        }

        return Map.of("SUCCESS", resultPno);
    }

}
