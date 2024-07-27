package com.gechan.fsrv.config;

import com.gechan.fsrv.config.formatter.LocalDateFormatter;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@Log4j2
public class CustomServletConfig implements WebMvcConfigurer {

    @Override
    public void addFormatters(FormatterRegistry registry) {
        log.debug("==========================================");
        log.debug("LocalDate, LocalTime Formatter");
        log.debug("==========================================");
        registry.addFormatter(new LocalDateFormatter());
    }
}
