package com.gechan.fsrv.service.impl;

import com.gechan.fsrv.domain.member.Member;
import com.gechan.fsrv.dto.MemberDTO;
import com.gechan.fsrv.dto.MemberRole;
import com.gechan.fsrv.repository.MemberRepository;
import com.gechan.fsrv.service.MemberService;
import org.springframework.stereotype.Service;

@Service
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;

    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    Member dtoToEntity(MemberDTO memberDTO) {
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

    @Override
    public void join(MemberDTO memberDTO) {
        Member member = this.dtoToEntity(memberDTO);
        memberRepository.save(member);
    }

    @Override
    public void login(String id, String password) {

    }

    @Override
    public void findById(String id) {

    }
}
