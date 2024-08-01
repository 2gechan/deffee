package com.gechan.fsrv.domain.member;

import com.gechan.fsrv.dto.MemberDTO;
import com.gechan.fsrv.dto.MemberRole;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "memberRoleList")
public class Member {

    @Id
    private String id;

    private String password;
    private String name;
    private String phone;
    private String team;
    private LocalDate signDate;

    private boolean useFlag;

    @ElementCollection(fetch = FetchType.LAZY)
    @Builder.Default
    List<MemberRole> memberRoleList = new ArrayList<>();

    public void addRole(MemberRole memberRole) {
        memberRoleList.add(memberRole);
    }

    public void clearRole() {
        memberRoleList.clear();
    }

    public Member dtoToEntity(MemberDTO memberDTO) {
        Member member = Member.builder()
                .id(memberDTO.getId())
                .name(memberDTO.getName())
                .password(memberDTO.getPassword())
                .phone(memberDTO.getPhone())
                .signDate(memberDTO.getSignDate())
                .build();
        member.addRole(MemberRole.USER);
        // 구장 운영자일 경우 체크 로직 필요

        return member;
    }

}
