package com.gechan.openmarket.security;

import com.gechan.openmarket.domain.Member;
import com.gechan.openmarket.dto.MemberDTO;
import com.gechan.openmarket.repository.MemberRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
@Log4j2
public class UserDetailServiceImpl implements UserDetailsService {

    private final MemberRepository memberRepository;

    public UserDetailServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Member member = memberRepository.getWithRoles(username);

        if(member == null) {
            throw new UsernameNotFoundException("Not Found");
        }

        MemberDTO memberDTO = new MemberDTO(
                member.getId(),
                member.getPw(),
                member.getNickname(),
                member.getMemberRoleList().stream().map(role -> role.name()).collect(Collectors.toList()) // role.name()은 enum 타입인 경우 상수의 이름을 반환한다.
        );

        return memberDTO;
    }
}
