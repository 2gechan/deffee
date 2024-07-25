package com.gechan.fsrv.domain;

import com.gechan.fsrv.dto.MemberRole;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.ToString;

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
    String id;

    String password;
    String name;
    String phone;
    String team;
    Date signDate;

    boolean useFlag;

    @ElementCollection(fetch = FetchType.LAZY)
    @Builder.Default
    List<MemberRole> memberRoleList = new ArrayList<>();

    public void addRole(MemberRole memberRole) {
        memberRoleList.add(memberRole);
    }

    public void clearRole() {
        memberRoleList.clear();
    }

}
