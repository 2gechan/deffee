package com.gechan.devlog.service.impl;

import com.gechan.devlog.dto.CreateBlogRequestDTO;
import com.gechan.devlog.dto.MemberDTO;
import com.gechan.devlog.repository.SimpleBlogRepository;
import com.gechan.devlog.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SimpleBlogService implements BlogService {


    private final SimpleBlogRepository simpleBlogRepository;

    @Autowired
    public SimpleBlogService(SimpleBlogRepository simpleBlogRepository) {
        this.simpleBlogRepository = simpleBlogRepository;
    }

    @Override
    public String createBlog(CreateBlogRequestDTO createBlogRequestDTO) {
        MemberDTO memberDTO = createBlogRequestDTO.getMemberDTO();

        // memberId를 기반으로 member Entity 찾기

        // member Entity를 통해 blog 생성 유무 확인
        // simpleBlogRepository.findByMemberBlog(null);

        // member 가 생성한 블로그가 없으면 생성
        // simpleBlogRepository.createBlog(null);

        return null;
    }
}
