package com.gechan.devlog.service;

import com.gechan.devlog.dto.BlogCreateRequestDTO;

public interface BlogService {

    public String createBlog(BlogCreateRequestDTO blogCreateRequestDTO);
}
