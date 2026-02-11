package com.gc.bank.domains.bank.service;

import com.gc.bank.domains.transaction.event.AccountTransactionEvent;
import com.gc.bank.domains.transaction.event.TransactionType;
import com.gc.bank.domains.bank.repository.BankAccountRepository;
import com.gc.bank.domains.bank.repository.BankMemberRepository;
import com.gc.bank.types.dto.ApiResponse;
import com.gc.bank.types.entity.Account;
import com.gc.bank.types.entity.Member;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

@Service
public class BankServiceImpl implements BankService {

    private final BankAccountRepository bankAccountRepository;
    private final BankMemberRepository bankMemberRepository;
    private final ApplicationEventPublisher eventPublisher;

    public BankServiceImpl(BankAccountRepository bankAccountRepository, BankMemberRepository bankMemberRepository, ApplicationEventPublisher eventPublisher) {
        this.bankAccountRepository = bankAccountRepository;
        this.bankMemberRepository = bankMemberRepository;
        this.eventPublisher = eventPublisher;
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

        eventPublisher.publishEvent(
                new AccountTransactionEvent(
                        memberId,
                        accountId,
                        amount,
                        TransactionType.DEPOSIT,
                        Instant.now()
                )
        );

    }

    @Transactional
    @Override
    public void withdraw(Long memberId, Long accountId, BigDecimal amount) {
        Account findAccount = bankAccountRepository.findByIdAndMember_Id(accountId, memberId)
                .orElseThrow(() -> new RuntimeException("출금할 계좌 정보가 올바르지 않습니다."));

        findAccount.withdraw(amount);

        eventPublisher.publishEvent(
            new AccountTransactionEvent(
                    memberId,
                    accountId,
                    amount,
                    TransactionType.WITHDRAW,
                    Instant.now()
            )
        );
    }
}
