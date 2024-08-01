package com.gechan.fsrv.repository;

import com.gechan.fsrv.config.CustomServletConfig;
import com.gechan.fsrv.domain.member.Member;
import com.gechan.fsrv.dto.MemberDTO;
import com.gechan.fsrv.dto.MemberRole;
import com.gechan.fsrv.service.MemberService;
import com.gechan.fsrv.service.impl.MemberServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

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
                    .signDate(LocalDate.parse("2024-08-01"))
                    .build();
            member.addRole(MemberRole.USER);
            if (i > 5) member.addRole(MemberRole.BUSINESS);
            if (i > 8) member.addRole(MemberRole.ADMIN);

            memberRepository.save(member);
        }
    }

    @DisplayName("memberDTO Entity 변환 후 DB 저장")
    @Test
    void dtoToEntityJoinTest() {
        MemberDTO memberDTO = MemberDTO.builder()
                .id("MEMBER_TEST")
                .name("TEST")
                .phone("010-1234-1234")
                .password("12341234")
                .signDate(LocalDate.parse("2024-08-01"))
                .build();

        Member member = new Member();
        member.dtoToEntity(memberDTO);

        memberRepository.save(member);
    }
}
