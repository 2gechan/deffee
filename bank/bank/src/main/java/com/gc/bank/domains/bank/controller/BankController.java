package com.gc.bank.domains.bank.controller;

import com.gc.bank.domains.bank.service.BankService;
import com.gc.bank.security.SecurityUtil;
import com.gc.bank.types.dto.ApiResponse;
import com.gc.bank.types.dto.MoneyRequest;
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

    @PostMapping("/createAccount")
    public ApiResponse<String>  createAccount(
            @AuthenticationPrincipal Long memberId
    ) {
        return bankService.createAccount(memberId);
    }

    @GetMapping("/balance")
    public BigDecimal balanceRetv(
            @AuthenticationPrincipal Long memberId,
            @RequestParam Long accountId
    ) {
        return bankService.balanceRetv(memberId, accountId);
    }

    @PostMapping("/deposit")
    public ApiResponse<String> deposit(
            @AuthenticationPrincipal Long memberId,
            @RequestBody MoneyRequest moneyRequest
    ) {

        bankService.deposit(memberId, moneyRequest.toAccountId(), moneyRequest.amount());
        return ApiResponse.success("OK");
    }

    @PostMapping("/withdraw")
    public ApiResponse<String> withdraw(
            @AuthenticationPrincipal Long memberId,
            @RequestBody MoneyRequest moneyRequest
            ) {
        bankService.withdraw(memberId, moneyRequest.fromAccountId(), moneyRequest.amount());
        return ApiResponse.success("OK");
    }

    @PostMapping("/transfer")
    public ApiResponse<String> transfer(
            @AuthenticationPrincipal Long memberId,
            @RequestBody MoneyRequest moneyRequest
    ) {

        bankService.transfer(
                memberId,
                moneyRequest.fromAccountId(),
                moneyRequest.toAccountId(),
                moneyRequest.amount()
        );

        return ApiResponse.success("OK");
    }
}
