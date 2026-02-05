package com.gc.bank.domains.bank.controller;

import com.gc.bank.domains.bank.service.BankService;
import com.gc.bank.types.dto.ApiResponse;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/api/v1/bank")
public class BankController {

    private final BankService bankService;

    public BankController(BankService bankService) {
        this.bankService = bankService;
    }

    @GetMapping("/createAccount")
    public void createAccount(Long memberSeq) {
        ApiResponse<String> response = bankService.createAccount(memberSeq);
    }

    @GetMapping("/balance")
    public void balanceRetv(Long memberSeq, Long accountSeq) {
        BigDecimal balance = bankService.balanceRetv(memberSeq, accountSeq);
    }

    @PostMapping("/deposit")
    public ApiResponse<String> deposit(
            @AuthenticationPrincipal Long memberId,
            @RequestParam Long accountId,
            @RequestParam BigDecimal amount
    ) {
        bankService.deposit(memberId, accountId, amount);
        return ApiResponse.success("OK");
    }

    @PostMapping("/withdraw")
    public ApiResponse<String> withdraw(
            @AuthenticationPrincipal Long memberId,
            @RequestParam Long accountId,
            @RequestParam BigDecimal amount
    ) {
        bankService.withdraw(memberId, accountId, amount);
        return ApiResponse.success("OK");
    }
}
