package com.gechan.devlog.service.impl;

import com.gechan.devlog.domain.Member;
import com.gechan.devlog.dto.MemberDTO;
import com.gechan.devlog.repository.DatabaseUserRepository;
import com.gechan.devlog.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private DatabaseUserRepository userRepository;

    @Override
    public Long join(MemberDTO memberDTO) {
        Member member = new Member(
                memberDTO.getEmail(),
                memberDTO.getPw(),
                memberDTO.getName(),
                memberDTO.getPhone()
        );
        Long id = userRepository.save(member).getId();
        return id;
    }
}
