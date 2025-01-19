package com.gechan.devlog.dto;

import com.gechan.devlog.domain.Member;

public class MemberDTO {

    private Long id;
    private String email;
    private String pw;
    private String name;
    private String phone;

    public MemberDTO(Long id, String email, String pw, String name, String phone) {
        this.id = id;
        this.email = email;
        this.pw = pw;
        this.name = name;
        this.phone = phone;
    }

    public static MemberDTO toDTO(Member member) {
        MemberDTO memberDTO = new MemberDTO(
                member.getId(),
                member.getEmail(),
                member.getPw(),
                member.getName(),
                member.getPhone()
        );
        return memberDTO;
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getPw() {
        return pw;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    static MemberDTO toDto(Member member) {
        MemberDTO memberDTO = new MemberDTO(
                member.getId(),
                member.getEmail(),
                member.getPw(),
                member.getName(),
                member.getPhone()
        );
        return memberDTO;
    }
}
