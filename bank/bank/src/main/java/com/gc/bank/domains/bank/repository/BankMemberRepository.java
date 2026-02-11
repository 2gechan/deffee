package com.gc.bank.domains.bank.repository;

import com.gc.bank.types.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BankMemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findByUsername(String githubId);
}
