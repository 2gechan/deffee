package com.gechan.fsrv.repository;

import com.gechan.fsrv.domain.Member;
import com.gechan.fsrv.dto.MemberRole;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

@SpringBootTest
public class MemberTest {

    @Autowired MemberRepository memberRepository;


    @DisplayName("유저 생성 후 DB 적재 테스트")
    @Test
    void createMemberTest() {
        for (int i = 1; i <= 10; i++) {
            Member member = Member.builder()
                    .id("MEMBER" + i)
                    .password("1234")
                    .name("MEMBER" + i)
                    .phone("010-1111-1111")
                    .signDate(new Date(System.currentTimeMillis()))
                    .build();
            member.addRole(MemberRole.USER);
            if (i > 5) member.addRole(MemberRole.BUSINESS);
            if (i > 8) member.addRole(MemberRole.ADMIN);

            memberRepository.save(member);
        }
    }
}
