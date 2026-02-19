package com.gc.bank.domains.bank.service;

import com.gc.bank.types.dto.ApiResponse;
import com.gc.bank.types.entity.Account;

import java.math.BigDecimal;
import java.util.List;

public interface BankService {

    ApiResponse<String> createAccount(Long memberId);

    BigDecimal balanceRetv(Long memberId, Long accountId);

    List<Account> accountsRetv(Long memberId);

    public void deposit(Long memberId, Long accountId, BigDecimal amount);

    public void withdraw(Long memberId, Long accountId, BigDecimal amount);

    public void transfer(Long fromAccountId, Long toAccountId, BigDecimal amount);
}
