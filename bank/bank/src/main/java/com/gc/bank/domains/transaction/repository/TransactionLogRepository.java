package com.gc.bank.domains.transaction.repository;

import com.gc.bank.types.entity.AccountTransactionLog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionLogRepository extends JpaRepository<AccountTransactionLog, Long> {

    Page<AccountTransactionLog> findByAccountIdAndMemberIdOrderByOccurredAtDesc(Long accountId, Long memberId, Pageable pageable);
}
