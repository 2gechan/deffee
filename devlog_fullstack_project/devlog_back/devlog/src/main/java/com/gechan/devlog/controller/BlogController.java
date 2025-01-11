package com.gechan.devlog.controller;

import com.gechan.devlog.dto.BlogCreateRequestDTO;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/devlog")
public class BlogController {


    @RequestMapping(method = RequestMethod.POST, value = "/")
    public void createBlog(@RequestBody BlogCreateRequestDTO blogCreateRequestDTO) {

    }
}
