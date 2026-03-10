package com._gechan.ticketing.types.dto;

public record MemberRequest(
        Long memberId,
        String email,
        String password
) {
}
