package com.gc.bank.domains.bank.service;

import com.gc.bank.domains.transaction.event.AccountTransactionEvent;
import com.gc.bank.domains.transaction.event.TransactionType;
import com.gc.bank.domains.bank.repository.BankAccountRepository;
import com.gc.bank.domains.bank.repository.BankMemberRepository;
import com.gc.bank.security.SecurityUtil;
import com.gc.bank.types.dto.ApiResponse;
import com.gc.bank.types.entity.Account;
import com.gc.bank.types.entity.Member;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.Optional;

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

    @Transactional
    @Override
    public void transfer(Long fromAccountId, Long toAccountId, BigDecimal amount) {

        if (fromAccountId.equals(toAccountId)) {
            throw new IllegalArgumentException("같은 계좌간의 이체는 불가 합니다.");
        }

        Long loginMemberId = SecurityUtil.getCurrentMemberId();

        // deadlock 방지를 위한 무조건적인 계좌번호 숫자가 낮은 순에서 높은 순으로의 조회
        // 1계좌 -> 2계좌 이체 시 1계좌 조회 후 2계좌 조회
        // 2계좌 -> 1계좌 다른 트랜잭션으로 접근 시 동일하게 1계좌 조회 후 2계좌 조회
        Long firstId = Math.min(fromAccountId, toAccountId);
        Long secondId = Math.max(fromAccountId, toAccountId);

        Account firstAccount = bankAccountRepository.findById(firstId).orElseThrow();

        Account secondAccount = bankAccountRepository.findById(secondId).orElseThrow();

        Account from = fromAccountId.equals(firstId) ? firstAccount : secondAccount;
        Account to = toAccountId.equals(secondId) ? secondAccount : firstAccount;

        // 본인 계좌 검증 추가
        if (!from.getMember().getId().equals(loginMemberId)) {
            throw new IllegalArgumentException("본인 계좌만 이체할 수 있습니다.");
        }

        from.withdraw(amount);
        to.deposit(amount);

        eventPublisher.publishEvent(
                new AccountTransactionEvent(
                        from.getMember().getId(),
                        from.getId(),
                        amount,
                        TransactionType.WITHDRAW,
                        Instant.now()
                )
        );

        eventPublisher.publishEvent(
                new AccountTransactionEvent(
                        to.getMember().getId(),
                        to.getId(),
                        amount,
                        TransactionType.DEPOSIT,
                        Instant.now()
                )
        );
    }
}
