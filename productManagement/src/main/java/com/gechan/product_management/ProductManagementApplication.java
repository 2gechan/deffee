package com.gechan.product_management;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.sql.Connection;

@SpringBootApplication
public class ProductManagementApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProductManagementApplication.class, args);
    }

//    @Bean
//    public ModelMapper modelMapper() {
//        ModelMapper modelMapper = new ModelMapper();
//        modelMapper.getConfiguration()
//                .setFieldAccessLevel(Configuration.AccessLevel.PRIVATE)
//                .setFieldMatchingEnabled(true);
//        return modelMapper;
//    }

    @Bean
    @Profile("prod")
    public ApplicationRunner runner(DataSource dataSource) {
        return args -> {
            Connection connection = dataSource.getConnection();
        };
    }
}
