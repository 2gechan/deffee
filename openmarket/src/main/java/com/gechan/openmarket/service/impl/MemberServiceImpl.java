package com.gechan.openmarket.service.impl;

import com.gechan.openmarket.domain.Member;
import com.gechan.openmarket.domain.MemberRole;
import com.gechan.openmarket.dto.MemberDTO;
import com.gechan.openmarket.repository.MemberRepository;
import com.gechan.openmarket.service.MemberService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    public MemberServiceImpl(MemberRepository memberRepository, PasswordEncoder passwordEncoder) {
        this.memberRepository = memberRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Member dtoToEntity(MemberDTO memberDTO) {
        Member member = Member.builder()
                .id(memberDTO.getId())
                .pw(passwordEncoder.encode(memberDTO.getPw()))
                .nickname(memberDTO.getNickname()).build();

        member.addRole(MemberRole.USER);

        return member;
    }

    @Override
    public MemberDTO entityToDTO(Member member) {
        MemberDTO memberDTO = MemberDTO.builder().
                id(member.getId())
                .pw(member.getPw())
                .nickname(member.getNickname())
                .build();

        for (MemberRole memberRole : member.getMemberRoleList()) {
            memberDTO.addRole(memberRole);
        }

        return memberDTO;
    }

    @Override
    public String join(MemberDTO memberDTO) {

        Member member = this.dtoToEntity(memberDTO);
        String id = memberRepository.save(member).getId();

        return id;
    }

    @Override
    public void modify(MemberDTO memberDTO) {

    }

    @Override
    public String delete(String id) {
        Optional<Member> findMember = memberRepository.findById(id);
        Member member = findMember.orElseThrow();

        memberRepository.delete(member);
        return id;
    }
}
