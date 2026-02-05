package com.gc.bank.domains.bank.repository;

import com.gc.bank.types.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BankAccountRepository extends JpaRepository<Account, Long> {

    Optional<Account> findByIdAndMember_Id(Long accountSeq, Long memberSeq);

    List<Account> findAllByMember_Id(Long memberId);
}
