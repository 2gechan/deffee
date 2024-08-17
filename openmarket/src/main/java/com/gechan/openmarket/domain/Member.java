package com.gechan.openmarket.domain;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import lombok.Builder;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Member {

    @Id
    private String id;

    private String pw;

    private String nickname;

    // 소셜 회원 가입은 나중에 생각

    @ElementCollection(fetch = FetchType.LAZY)
    @Builder.Default
    private List<MemberRole> memberRoleList = new ArrayList<>();

    public void addRole(MemberRole memberRole) {
        memberRoleList.add(memberRole);
    }

    public void clearRole() {
        memberRoleList.clear();
    }
}
