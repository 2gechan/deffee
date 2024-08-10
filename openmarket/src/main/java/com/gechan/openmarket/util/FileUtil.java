package com.gechan.openmarket.util;

import jakarta.annotation.PostConstruct;
import lombok.extern.log4j.Log4j2;
import net.coobird.thumbnailator.Thumbnails;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
@Log4j2
public class FileUtil {

    @Value("${com.gechan.upload.path}")
    private String uploadPath;

    @PostConstruct // Spring Container 에 FileUtil 이 Bean 으로 등록 될때 실행 되는 Method
    public void init() {
        // uploadPath 가 가리키는 파일 또는 디렉터리의 경로를 기반으로 File 객체 생성
        File tmpFolder = new File(uploadPath);

        if (!tmpFolder.exists()) tmpFolder.mkdir(); // tmpFolder 경로가 존재 하지 않으면 디렉터리 생성

        uploadPath = tmpFolder.getAbsolutePath(); // 파일이 저장되는 실제 경로

    }

    public List<String> saveFiles(List<MultipartFile> files) throws RuntimeException {
        if (files == null || files.isEmpty()) {
           return null;
        }

        List<String> uploadFileNames = new ArrayList<>();

        for (MultipartFile file : files) {

            String saveName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();

            Path savePath = Paths.get(uploadPath, saveName); // 파일을 저장할 경로와 저장할 파일 이름명 설정

            try {

                // file.getInputStream() 은 file 객체로 부터 입력 스트림을 가져와 파일의 내용을 바이트 단위로 읽을 수 있게 한다.
                Files.copy(file.getInputStream(), savePath); // 입력 스트림에서 데이터를 읽어 지정된 경로에 파일로 저장

                String contentType = file.getContentType();
                if (contentType == null || contentType.startsWith("image")) {
                    Path thumbnailPath = Paths.get(uploadPath, "s_" + saveName);

                    // 프론트 단에서 이미지를 불러올 때 "s_" + 파일명으로 불러오면 원본 파일 대신 조정된 이미지를 불러올 수 있다.

                    Thumbnails.of(savePath.toFile()).size(200, 200).toFile(thumbnailPath.toFile());
                }

                uploadFileNames.add(saveName);

            } catch (IOException e) {
                throw new RuntimeException();
            }

        }

        return uploadFileNames;
    }

    public ResponseEntity<Resource> getFile(String fileName) throws RuntimeException {

        Resource resource = new FileSystemResource(uploadPath + File.separator + fileName);

        // 이미지가 없거나, 파일을 읽을 수 없는 경우 default 이미지로 전환
        if (!resource.isReadable()) {
            resource = new FileSystemResource(uploadPath + File.separator + "default.jpg");
        }

        HttpHeaders header = new HttpHeaders();
        try {
            // 파일의 MIME 타입 설정, resource 객체를 파일로 변환하고 경로 얻기
            header.add("Content-Type", Files.probeContentType(resource.getFile().toPath()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return ResponseEntity.ok().headers(header).body(resource);
    }
}
