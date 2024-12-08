package com.gechan.devlog.dto;

public class CreateBlogRequestDTO {

    private String blogName;
    private MemberDTO memberDTO;

    public CreateBlogRequestDTO(String blogName, MemberDTO memberDTO) {
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
