package com.gc.bank.domains.bank.service;

import com.gc.bank.domains.bank.repository.BankAccountRepository;
import com.gc.bank.domains.bank.repository.BankMemberRepository;
import com.gc.bank.types.dto.ApiResponse;
import com.gc.bank.types.entity.Account;
import com.gc.bank.types.entity.Member;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class BankServiceImpl implements BankService {

    private final BankAccountRepository bankAccountRepository;
    private final BankMemberRepository bankMemberRepository;

    public BankServiceImpl(BankAccountRepository bankAccountRepository, BankMemberRepository bankMemberRepository) {
        this.bankAccountRepository = bankAccountRepository;
        this.bankMemberRepository = bankMemberRepository;
    }

    @Override
    @Transactional(readOnly = false)
    public ApiResponse<String> createAccount(Long memberSeq) {

        String accountNumber = generateAccountNumber();

        Member member = bankMemberRepository.findById(memberSeq).orElseThrow(() -> new RuntimeException("member not found"));

        Account account = new Account(accountNumber, member);

        bankAccountRepository.save(account);


        return ApiResponse.success("SUCCESS");
    }

    @Override
    public BigDecimal balanceRetv(Long memberSeq, Long accountSeq) {
        return null;
    }

    @Transactional(readOnly = true)
    @Override
    public List<Account> accountsRetv(Long memberId) {

        return bankAccountRepository.findAllByMember_Id(memberId);

    }

    private String generateAccountNumber() {
        String bankCode = "123";
        int middleNumber = (int) (Math.random() * 9000 + 1000);
        int lastNumber = (int)(Math.random() * 900000 + 100000);
        return bankCode + "-" + middleNumber + "-" + lastNumber;

    }

    @Transactional
    @Override
    public void deposit(Long memberId, Long accountId, BigDecimal amount) {
        Account findAccount = bankAccountRepository.findByIdAndMember_Id(accountId, memberId)
                .orElseThrow(() -> new RuntimeException("입금할 계좌 정보가 올바르지 않습니다."));

        findAccount.deposit(amount);
    }

    @Transactional
    @Override
    public void withdraw(Long memberId, Long accountId, BigDecimal amount) {
        Account findAccount = bankAccountRepository.findByIdAndMember_Id(accountId, memberId)
                .orElseThrow(() -> new RuntimeException("출금할 계좌 정보가 올바르지 않습니다."));

        findAccount.withdraw(amount);
    }
}
