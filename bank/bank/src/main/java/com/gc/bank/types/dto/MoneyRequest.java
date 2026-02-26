package com.gc.bank.types.dto;

import java.math.BigDecimal;

public record MoneyRequest(
        Long fromAccountId,
        Long toAccountId,
        BigDecimal amount
) {

}
