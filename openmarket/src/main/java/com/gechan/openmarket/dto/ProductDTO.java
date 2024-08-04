package com.gechan.openmarket.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductDTO {

    private Long pno;

    private String pname;

    private int price;

    private String pdesc;

    private boolean sellFlag;

    private LocalDate uploadDate;

    @Builder.Default
    List<String> uploadFileNames = new ArrayList<>(); // 기존에 저장중인 파일명 리스트

    @Builder.Default
    List<MultipartFile> files = new ArrayList<>(); // 이미지 추가 필요 시 사용할 리스트

}
