package com.gechan.fsrv.util;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;

@Component
@Log4j2
public class FileUtil {

    @Value("${com.gechan.upload.path}")
    private String uploadPath;

    public void init() {
        File tmpFolder = new File(uploadPath);

        if (!tmpFolder.exists()) {
            tmpFolder.mkdirs();
        }

        uploadPath = tmpFolder.getAbsolutePath();

        log.debug("=".repeat(50));
        log.debug("업로드 PATH {}", uploadPath);
        log.debug("=".repeat(50));
    }
}
