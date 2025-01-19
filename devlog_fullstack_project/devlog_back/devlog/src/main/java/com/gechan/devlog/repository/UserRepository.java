package com.gechan.devlog.repository;

import com.gechan.devlog.domain.Member;
import com.gechan.devlog.dto.UserLoginRequestDTO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<Member, Long> {

    Optional<Member> findByEmailAndPw(String email, String pw);

    Optional<Member> findByEmail(String email);
}
