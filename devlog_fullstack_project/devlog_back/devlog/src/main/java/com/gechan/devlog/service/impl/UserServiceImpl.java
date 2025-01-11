package com.gechan.devlog.service.impl;

import com.gechan.devlog.domain.Member;
import com.gechan.devlog.dto.UserCreateRequestDTO;
import com.gechan.devlog.repository.DatabaseUserRepository;
import com.gechan.devlog.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final DatabaseUserRepository userRepository;

    @Override
    public Long join(UserCreateRequestDTO userCreateRequestDTO) {
        Member member = new Member(
                userCreateRequestDTO.getEmail(),
                userCreateRequestDTO.getPw(),
                userCreateRequestDTO.getName(),
                userCreateRequestDTO.getPhone()
        );

        Long id = userRepository.save(member).getId();
        return id;
    }
}
