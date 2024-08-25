package com.gechan.openmarket.service;

import com.gechan.openmarket.domain.Member;
import com.gechan.openmarket.dto.MemberDTO;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface MemberService {

    String join(MemberDTO memberDTO);

    void modify(MemberDTO memberDTO);

    String delete(String id);

    Member dtoToEntity(MemberDTO memberDTO);

    MemberDTO entityToDTO(Member member);
}
