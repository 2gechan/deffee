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


    @Override
    public void join(MemberDTO memberDTO) {
        Member member = new Member();
        member.dtoToEntity(memberDTO);

        memberRepository.save(member);
    }

    @Override
    public void login(String id, String password) {

    }

    @Override
    public void findById(String id) {

    }
}
