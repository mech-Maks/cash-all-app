package com.tinkoff.com.tinkoff.financialtracker.utils;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum CurrencyRelationship {
    RUB(1L, 1L),
    USD(2L, 61L),
    EUR(3L, 62L);

    private final Long id;
    private final Long relationship;

    public static CurrencyRelationship of(Long id) {
        return switch (Math.toIntExact(id)) {
            case (1) -> RUB;
            case (2) -> USD;
            case (3) -> EUR;
            default -> null;
        };
    }
}
