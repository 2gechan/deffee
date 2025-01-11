package com.gechan.devlog.dto;

public class BlogCreateRequestDTO {

    private String blogName;
    private MemberDTO memberDTO;

    public BlogCreateRequestDTO(String blogName, MemberDTO memberDTO) {
        this.blogName = blogName;
        this.memberDTO = memberDTO;
    }

    public String getBlogName() {
        return blogName;
    }

    public MemberDTO getMemberDTO() {
        return memberDTO;
    }
}
