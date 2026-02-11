package com.gc.bank.domains.transaction.repository;

import com.gc.bank.types.entity.AccountTransactionLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionLogRepository extends JpaRepository<AccountTransactionLog, Long> {
}
