package com.gechan.devlog.service.impl;

import com.gechan.devlog.domain.Member;
import com.gechan.devlog.dto.MemberDTO;
import com.gechan.devlog.dto.UserCreateRequestDTO;
import com.gechan.devlog.dto.UserLoginRequestDTO;
import com.gechan.devlog.repository.UserRepository;
import com.gechan.devlog.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public Long join(UserCreateRequestDTO userCreateRequestDTO) {
        Member member = new Member(
                userCreateRequestDTO.getEmail(),
                userCreateRequestDTO.getPw(),
                userCreateRequestDTO.getName(),
                userCreateRequestDTO.getPhone()
        );

        Optional<Member> findMember = userRepository.findByEmail(member.getEmail());

        if (findMember.isPresent()) {
            throw new RuntimeException("중복 이메일 회원이 존재합니다.");
        }

        Long id = userRepository.save(member).getId();
        return id;
    }

    @Override
    public MemberDTO login(UserLoginRequestDTO userLoginRequestDTO) {
        Optional<Member> findMember = userRepository.findByEmailAndPw(userLoginRequestDTO.getEmail(), userLoginRequestDTO.getPw());
        Member member;
        if (findMember.isPresent()) {
            member = findMember.get();
        }
        else {
            throw new RuntimeException("회원 정보가 일치하지 않습니다.");
        }
        MemberDTO memberDTO = MemberDTO.toDTO(member);

        return memberDTO;
    }
}
